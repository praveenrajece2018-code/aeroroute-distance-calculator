package com.aeroroute.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistanceResponse {

    private String sourceCode;
    private String sourceAirport;
    private String destinationCode;
    private String destinationAirport;
    private Double distanceKm;
    private Double distanceMiles;
}