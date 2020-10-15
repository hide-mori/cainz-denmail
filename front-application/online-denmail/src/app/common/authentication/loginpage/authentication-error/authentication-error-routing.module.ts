import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthenticationErrorPage } from './authentication-error.page';

const routes: Routes = [
  {
    path: '',
    component: AuthenticationErrorPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthenticationErrorPageRoutingModule {}
