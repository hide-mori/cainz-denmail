import { NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule,Router } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { SessionTimeoutComponent } from './session-timeout.component';

@NgModule({
  imports: [ CommonModule, FormsModule, IonicModule, RouterModule],
  declarations: [SessionTimeoutComponent],
  exports: [SessionTimeoutComponent]
})
export class SessionTimeoutComponentModule {

}