import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RenrakuDetailPageRoutingModule } from './renraku-detail-routing.module';

import { RenrakuDetailPage } from './renraku-detail.page';

import {DateF2Pipe} from '../../pipes/date-formate2.pipe';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RenrakuDetailPageRoutingModule
  ],
  declarations: [RenrakuDetailPage, DateF2Pipe]
})
export class RenrakuDetailPageModule {}
