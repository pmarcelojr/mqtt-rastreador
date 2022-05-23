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

  getLocalizacoes(de: Date, para: Date): Observable<any> {

    const dataInicio= de.toISOString().split('T')[0]
    const dataFim = para.toISOString().split('T')[0]
    const url = `/?de=${dataInicio}&para=${dataFim}`;
    console.log(url);
    return this.http.get(this.gerarPath(url));
  }

  getRecentes(): Observable<any> {
    return this.http.get(this.gerarPath('/recentes'));
  }

}
