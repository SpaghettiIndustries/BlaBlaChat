import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private readonly GET_URL = `${environment.baseUrl}/message/get`;
  private readonly SEND_URL = `${environment.baseUrl}/message/send`;

  constructor(private http: HttpClient) { }

  get(topicId: number, numberOfMessages: number, startIndex?: number) {

  }

  sendStartingConversation(receiverId: number, content: string) {
    return this.http.post<any>(this.SEND_URL, {
      receiverId,
      topicId: '',
      content
    }).pipe(map(message => {
      return message;
    }));
  }

  sendExistingConversation(topicId: number, content: string) {
    return this.http.post<any>(this.SEND_URL, {
      receiverId: '',
      topicId,
      content
    }).pipe(map(message => {
      return message;
    }));
  }
}
