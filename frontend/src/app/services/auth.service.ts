import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, senha: string): Observable<any> {
    return this.http.post(environment.backendUrl + '/auth/login', {
      username,
      senha
    }, httpOptions);
  }

  register(username: string, email: string, senha: string): Observable<any> {
    return this.http.post(environment.backendUrl + '/user', {
      username, email, senha
    }, httpOptions);
  }

}
