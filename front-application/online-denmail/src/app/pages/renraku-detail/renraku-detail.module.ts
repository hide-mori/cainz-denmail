import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RenrakuDetailPageRoutingModule } from './renraku-detail-routing.module';

import { RenrakuDetailPage } from './renraku-detail.page';

import {DateFPipe} from '../../pipes/date-formate.pipe';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RenrakuDetailPageRoutingModule
  ],
  declarations: [RenrakuDetailPage, DateFPipe]
})
export class RenrakuDetailPageModule {}
