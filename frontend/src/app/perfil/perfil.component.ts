import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  usuarioAtual: any = {
    username: null,
    token: null,
  };

  constructor(private token: TokenStorageService) { }

  ngOnInit(): void {
    this.usuarioAtual.username = this.token.getUser();
    this.usuarioAtual.token = this.token.getToken();
  }

}
