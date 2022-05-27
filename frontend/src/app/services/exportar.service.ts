import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ExportarService {

  constructor(private http: HttpClient) { }

  public gerarPath(caminho: string): string {
    return environment.backendUrl + '/exportar' + caminho;
  }

  exportarConfig(nome: string) {
    return this.http.get(this.gerarPath('/config/' + nome));
  }
}
