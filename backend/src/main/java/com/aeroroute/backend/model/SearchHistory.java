package com.aeroroute.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "search_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_code")
    private String sourceCode;

    @Column(name = "destination_code")
    private String destinationCode;

    @Column(name = "distance_km")
    private Double distanceKm;

    @Column(name = "distance_miles")
    private Double distanceMiles;

    @Column(name = "searched_at")
    private LocalDateTime searchedAt;
}