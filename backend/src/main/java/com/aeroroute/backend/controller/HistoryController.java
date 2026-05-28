package com.aeroroute.backend.controller;

import com.aeroroute.backend.model.SearchHistory;
import com.aeroroute.backend.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HistoryController {

    private final SearchHistoryRepository searchHistoryRepository;

    @GetMapping
    public List<SearchHistory> getRecentSearches() {
        return searchHistoryRepository.findTop10ByOrderBySearchedAtDesc();
    }
}