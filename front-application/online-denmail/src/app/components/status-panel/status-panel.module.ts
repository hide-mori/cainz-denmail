import { NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule,Router } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { StatusPanelComponent } from './status-panel.component';

/*
import { StatusCardComponentModule } from '../../components/status-card/status-card.module';
*/
@NgModule({
  imports: [ CommonModule, FormsModule, IonicModule, RouterModule],
  declarations: [StatusPanelComponent],
  exports: [StatusPanelComponent]
})
export class StatusPanelComponentModule {

}