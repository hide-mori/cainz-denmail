import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RenrakuListPage } from './renraku-list.page';

const routes: Routes = [
  {
    path: '',
    component: RenrakuListPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RenrakuListPageRoutingModule {}
