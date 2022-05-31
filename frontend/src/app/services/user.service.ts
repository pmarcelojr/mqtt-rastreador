import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  private gerarPath(caminho: string): string {
    return environment.backendUrl + '/user' + caminho;
  }

  getDados(): Observable<any> {
    return this.http.get(this.gerarPath('/'));
  }
}
