import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SessionTimeoutPage } from './session-timeout.page';

const routes: Routes = [
  {
    path: '',
    component: SessionTimeoutPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SessionTimeoutPageRoutingModule {}
