import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private permissoes: string[] = [];
  isConectado = false;
  exibirAdmin = false;
  username = '';

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isConectado = !!this.tokenStorageService.getToken();

    if (this.isConectado) {
      const user = this.tokenStorageService.getUser();
      this.username = this.tokenStorageService.getUser();
      this.permissoes = user.permissoes || [];
      this.exibirAdmin = this.permissoes.includes('ROLE_ADMIN');
    }

  }

  desconectar(): void {
    this.tokenStorageService.desconectar();
    window.location.reload();
  }
}
