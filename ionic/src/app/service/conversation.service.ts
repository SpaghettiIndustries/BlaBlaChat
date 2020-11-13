import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConversationService {
  private readonly GET_ALL_URL = `${environment.baseUrl}/topic/get_all`;
  private readonly GET_RECENT_URL = `${environment.baseUrl}/topic/get_recent`;

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.post<any>(this.GET_ALL_URL, { });
  }

  getRecent(numberOfTopics: number, startIndex?: number) {
    return this.http.post<any>(this.GET_RECENT_URL, {
      numberOfTopics,
      startIndex: startIndex !== undefined ? startIndex : ''
    });
  }
}
