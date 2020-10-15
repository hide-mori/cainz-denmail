import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlertComponent } from './alert/alert.component';
import { AuthenticationErrorComponent } from '../authentication/login/authentication-error/authentication-error.component';
import { NotFoundComponent } from './error/not-found/not-found.component';
import { SystemErrorComponent } from './error/system-error/system-error.component';

@NgModule({
  declarations: [
    AlertComponent,
    AuthenticationErrorComponent,
    NotFoundComponent,
    SystemErrorComponent,
  ],
  imports: [CommonModule],
  exports: [
    AlertComponent,
    AuthenticationErrorComponent,
    NotFoundComponent,
    SystemErrorComponent,
  ],
})
export class ComponentsModule {}
