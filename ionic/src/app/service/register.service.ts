import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private readonly REGISTER_URL = `${environment.baseUrl}/user/register`;

  constructor(private http: HttpClient) { }

  register(nick: string, password: string, email?: string): Observable<any> {
    return this.http.post(this.REGISTER_URL, {
      'nick': nick,
      'password': password,
      
    });
  }

}
