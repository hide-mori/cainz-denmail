import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RenrakuDetailPage } from './renraku-detail.page';

const routes: Routes = [
  {
    path: '',
    component: RenrakuDetailPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RenrakuDetailPageRoutingModule {}
