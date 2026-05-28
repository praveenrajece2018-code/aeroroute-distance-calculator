import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { finalize, take } from 'rxjs';

import { Airport } from './models/airport';
import { DistanceResponse } from './models/distance-response';
import { SearchHistory } from './models/search-history';

import { AirportService } from './services/airport';
import { DistanceService } from './services/distance';
import { HistoryService } from './services/history';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  airports: Airport[] = [];
  sourceCode = '';
  destinationCode = '';

  result: DistanceResponse | null = null;
  history: SearchHistory[] = [];

  loading = false;
  errorMessage = '';

  constructor(
    private airportService: AirportService,
    private distanceService: DistanceService,
    private historyService: HistoryService
  ) {}

  ngOnInit(): void {
    this.loadAirports();
    this.loadHistory();
  }

  loadAirports(): void {
    this.airportService.getAirports().pipe(take(1)).subscribe({
      next: (data: Airport[]) => {
        this.airports = data;
      },
      error: (error) => {
        console.error('Airport API error:', error);
        this.errorMessage = 'Failed to load airports. Please check backend.';
      }
    });
  }

  calculateDistance(): void {
    this.errorMessage = '';
    this.result = null;

    if (!this.sourceCode || !this.destinationCode) {
      this.errorMessage = 'Please select both source and destination airports.';
      return;
    }

    if (this.sourceCode === this.destinationCode) {
      this.errorMessage = 'Source and destination airport cannot be the same.';
      return;
    }

    this.loading = true;

    this.distanceService.calculateDistance(this.sourceCode, this.destinationCode)
      .pipe(
        take(1),
        finalize(() => {
          this.loading = false;
        })
      )
      .subscribe({
        next: (data: DistanceResponse) => {
          console.log('Distance response:', data);
          this.result = data;
          this.loadHistory();
        },
        error: (error) => {
          console.error('Distance API error:', error);
          this.errorMessage = 'Distance calculation failed. Please check backend.';
        }
      });
  }

  loadHistory(): void {
    this.historyService.getRecentSearches().pipe(take(1)).subscribe({
      next: (data: SearchHistory[]) => {
        this.history = data;
      },
      error: (error) => {
        console.error('History API error:', error);
      }
    });
  }

  resetForm(): void {
    this.sourceCode = '';
    this.destinationCode = '';
    this.result = null;
    this.errorMessage = '';
    this.loading = false;
  }
}