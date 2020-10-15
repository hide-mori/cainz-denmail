import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import {AuthenticationGuard} from './common/authentication/authentication.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'renraku-list',
    pathMatch: 'full'
  },
  {
    path: 'login/session-timeout',
    loadChildren: () => import('./common/authentication/loginpage/session-timeout/session-timeout.module').then( m => m.SessionTimeoutPageModule)
  },
  {
    path: 'login/authentication-error/:msg',
    loadChildren: () => import('./common/authentication/loginpage/authentication-error/authentication-error.module').then( m => m.AuthenticationErrorPageModule)
  },
  {
    path: 'renraku-list',
    canActivate: [AuthenticationGuard],
    loadChildren: () => import('./pages/renraku-list/renraku-list.module').then( m => m.RenrakuListPageModule)
  },
  {
    path: 'renraku-detail/:kanrino/:statusId',
    canActivate: [AuthenticationGuard],
    loadChildren: () => import('./pages/renraku-detail/renraku-detail.module').then( m => m.RenrakuDetailPageModule)
  }, 
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes /*, { preloadingStrategy: PreloadAllModules }*/)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
