package com.aeroroute.backend.repository;

import com.aeroroute.backend.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByIataCodeIgnoreCase(String iataCode);

    List<Airport> findByIataCodeContainingIgnoreCaseOrNameContainingIgnoreCaseOrCityContainingIgnoreCase(
            String iataCode,
            String name,
            String city
    );
}