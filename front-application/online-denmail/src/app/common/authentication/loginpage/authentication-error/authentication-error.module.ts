import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AuthenticationErrorPageRoutingModule } from './authentication-error-routing.module';

import { AuthenticationErrorPage } from './authentication-error.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AuthenticationErrorPageRoutingModule
  ],
  declarations: [AuthenticationErrorPage]
})
export class AuthenticationErrorPageModule {}
