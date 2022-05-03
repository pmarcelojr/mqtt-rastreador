import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {
  form: any = {
    nome: null,
    username: null,
    email: null,
    senha: null
  };

  isSucesso = false;
  isCadastroFalhou = false;
  mensagemErro = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { username, email, senha, nome } = this.form;
    this.authService.register(username, email, senha, nome).subscribe(
      data => {
        this.isSucesso = true;
        this.isCadastroFalhou = false;
      },
      err => {
        this.isSucesso = false;
        this.mensagemErro = err.error.message;
        this.isCadastroFalhou = true;
      }
    );
  }

}
