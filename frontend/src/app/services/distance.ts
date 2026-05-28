import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DistanceResponse } from '../models/distance-response';

@Injectable({
  providedIn: 'root'
})
export class DistanceService {
  private apiUrl = 'http://localhost:8080/api/distance';

  constructor(private http: HttpClient) {}

  calculateDistance(from: string, to: string): Observable<DistanceResponse> {
    return this.http.get<DistanceResponse>(`${this.apiUrl}?from=${from}&to=${to}`);
  }
}