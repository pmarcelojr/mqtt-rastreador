import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DispositivoService {
  constructor(private http: HttpClient) { }

  private gerarPath(caminho: string): string {
    return environment.backendUrl + '/dispositivo' + caminho;
  }

  criarDispositivo(form: any): Observable<any> {
    return this.http.post(this.gerarPath("/"), form, {
      headers: {
        'Content-Type': 'application/json',
      }
    });
  }

  getDispositivos(): Observable<any> {
    return this.http.get(this.gerarPath("/"));
  }
}
