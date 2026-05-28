package com.aeroroute.backend.controller;

import com.aeroroute.backend.dto.AirportResponse;
import com.aeroroute.backend.model.Airport;
import com.aeroroute.backend.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AirportController {

    private final AirportRepository airportRepository;

    @GetMapping
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @GetMapping("/search")
    public List<AirportResponse> searchAirports(@RequestParam String query) {
        return airportRepository
                .findByIataCodeContainingIgnoreCaseOrNameContainingIgnoreCaseOrCityContainingIgnoreCase(
                        query,
                        query,
                        query
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private AirportResponse mapToResponse(Airport airport) {
        return AirportResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .name(airport.getName())
                .city(airport.getCity())
                .country(airport.getCountry())
                .latitude(airport.getLatitude())
                .longitude(airport.getLongitude())
                .build();
    }
}