import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RenrakuListPageRoutingModule } from './renraku-list-routing.module';

import { RenrakuListPage } from './renraku-list.page';

import { StatusCardComponentModule } from '../../components/status-card/status-card.module';
import { StatusPanelComponentModule } from '../../components/status-panel/status-panel.module';

import {DateFPipe} from '../../pipes/date-formate.pipe';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    StatusCardComponentModule,
    StatusPanelComponentModule,
    RenrakuListPageRoutingModule
  ],
  declarations: [RenrakuListPage, DateFPipe]
})
export class RenrakuListPageModule {}
