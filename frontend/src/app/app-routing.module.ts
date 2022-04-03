import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PerfilComponent } from './perfil/perfil.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { LoginActivateService } from './services/login-activate.service';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [LoginActivateService] },
  { path: 'login', component: LoginComponent},
  { path: 'registrar', component: RegistrarComponent},
  { path: 'perfil', component: PerfilComponent, canActivate: [LoginActivateService] },
  { path: '', redirectTo: 'home', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
