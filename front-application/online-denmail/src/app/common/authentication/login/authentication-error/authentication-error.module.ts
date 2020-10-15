import { NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule,Router } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { AuthenticationErrorComponent } from './authentication-error.component';

@NgModule({
  imports: [ CommonModule, FormsModule, IonicModule, RouterModule],
  declarations: [AuthenticationErrorComponent],
  exports: [AuthenticationErrorComponent]
})
export class AuthenticationErrorComponentModule {

}