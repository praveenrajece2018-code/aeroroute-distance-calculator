package com.aeroroute.backend.service;

import com.aeroroute.backend.dto.DistanceResponse;
import com.aeroroute.backend.model.Airport;
import com.aeroroute.backend.model.SearchHistory;
import com.aeroroute.backend.repository.AirportRepository;
import com.aeroroute.backend.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DistanceService {

    private final AirportRepository airportRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    public DistanceResponse calculateDistance(String sourceCode, String destinationCode) {

        if (sourceCode.equalsIgnoreCase(destinationCode)) {
            throw new IllegalArgumentException("Source and destination airport cannot be the same.");
        }

        Airport source = airportRepository.findByIataCodeIgnoreCase(sourceCode)
                .orElseThrow(() -> new RuntimeException("Source airport not found: " + sourceCode));

        Airport destination = airportRepository.findByIataCodeIgnoreCase(destinationCode)
                .orElseThrow(() -> new RuntimeException("Destination airport not found: " + destinationCode));

        double distanceKm = haversine(
                source.getLatitude(),
                source.getLongitude(),
                destination.getLatitude(),
                destination.getLongitude()
        );

        double distanceMiles = distanceKm * 0.621371;

        SearchHistory history = SearchHistory.builder()
                .sourceCode(source.getIataCode())
                .destinationCode(destination.getIataCode())
                .distanceKm(round(distanceKm))
                .distanceMiles(round(distanceMiles))
                .searchedAt(LocalDateTime.now())
                .build();

        searchHistoryRepository.save(history);

        return DistanceResponse.builder()
                .sourceCode(source.getIataCode())
                .sourceAirport(source.getName())
                .destinationCode(destination.getIataCode())
                .destinationAirport(destination.getName())
                .distanceKm(round(distanceKm))
                .distanceMiles(round(distanceMiles))
                .build();
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int earthRadiusKm = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2)
                        * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadiusKm * c;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}