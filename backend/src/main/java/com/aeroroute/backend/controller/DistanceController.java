package com.aeroroute.backend.controller;

import com.aeroroute.backend.dto.DistanceResponse;
import com.aeroroute.backend.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/distance")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DistanceController {

    private final DistanceService distanceService;

    @GetMapping
    public DistanceResponse calculateDistance(
            @RequestParam String from,
            @RequestParam String to
    ) {
        return distanceService.calculateDistance(from, to);
    }
}