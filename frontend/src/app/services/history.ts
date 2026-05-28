import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SearchHistory } from '../models/search-history';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  private apiUrl = 'http://localhost:8080/api/history';

  constructor(private http: HttpClient) {}

  getRecentSearches(): Observable<SearchHistory[]> {
    return this.http.get<SearchHistory[]>(this.apiUrl);
  }
}