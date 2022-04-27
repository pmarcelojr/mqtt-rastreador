import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './config/dashboard/dashboard.component';
import { DispositivosComponent } from './config/dispositivos/dispositivos.component';
import { ConfiguracaoComponent } from './configuracao/configuracao.component';
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
  {
    path: 'configuracao',
    component: ConfiguracaoComponent,
    canActivate: [LoginActivateService],
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'dispositivos',
        component: DispositivosComponent
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      }
    ]
  },
  { path: '', redirectTo: 'home', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
