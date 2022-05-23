import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { HomeComponent } from './home/home.component';
import { PerfilComponent } from './perfil/perfil.component';
import { DashboardAdminComponent } from './dashboard-admin/dashboard-admin.component';
import { DashboardModeradorComponent } from './dashboard-moderador/dashboard-moderador.component';
import { DashboardUserComponent } from './dashboard-user/dashboard-user.component';
import { AuthInterceptorProviders } from './helpers/auth.interceptor';
import { DashboardComponent } from './config/dashboard/dashboard.component';
import { ConfiguracaoComponent } from './configuracao/configuracao.component';
import { DispositivosComponent } from './config/dispositivos/dispositivos.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrarComponent,
    HomeComponent,
    PerfilComponent,
    DashboardAdminComponent,
    DashboardModeradorComponent,
    DashboardUserComponent,
    DashboardComponent,
    DispositivosComponent,
    ConfiguracaoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatFormFieldModule,
  ],
  providers: [AuthInterceptorProviders,
    {
      provide: MAT_DATE_LOCALE, useValue: 'pt-BR',
    }
  ],
  bootstrap: [AppComponent],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule { }
