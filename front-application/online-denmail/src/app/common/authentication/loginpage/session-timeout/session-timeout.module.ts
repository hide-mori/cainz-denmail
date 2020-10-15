import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { SessionTimeoutPageRoutingModule } from './session-timeout-routing.module';

import { SessionTimeoutPage } from './session-timeout.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    SessionTimeoutPageRoutingModule
  ],
  declarations: [SessionTimeoutPage]
})
export class SessionTimeoutPageModule {}
