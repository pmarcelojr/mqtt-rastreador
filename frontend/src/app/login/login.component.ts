import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    senha: null
  };

  isConectado = false;
  isLoginFalhou = false;
  mensagemErro = '';
  permissoes: string[] = [];

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isConectado = true;
      // obter permissoes aqui
    }
  }

  onSubmit(): void {
    const { username, senha } = this.form;
    this.authService.login(username, senha).subscribe(
      data => {
        this.tokenStorage.salvarToken(data.token);
        this.tokenStorage.saveUser(username);
        this.isLoginFalhou = false;
        this.isConectado = true;
        // obter permissões aqui
        this.recarregarPagina();
      },
      err => {
        this.mensagemErro = err.error.message;
        this.isLoginFalhou = true;
      }
    )
  }

  private recarregarPagina(): void {
    window.location.reload();
  }

}
