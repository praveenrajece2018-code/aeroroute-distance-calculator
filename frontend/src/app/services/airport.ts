import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Airport } from '../models/airport';

@Injectable({
  providedIn: 'root'
})
export class AirportService {

  private apiUrl = 'http://localhost:8080/api/airports';

  constructor(private http: HttpClient) {}

  getAirports(): Observable<Airport[]> {
    return this.http.get<Airport[]>(this.apiUrl);
  }
}