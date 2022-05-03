import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LocalizacaoService {

  constructor(private http: HttpClient) { }

  private gerarPath(caminho: string): string {
    return environment.backendUrl + '/localizacao' + caminho;
  }

  getLocalizacoes(): Observable<any> {
    return this.http.get(this.gerarPath('/'));
  }
}
