import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/common/services/auth.service';

@Component({
  selector: 'mbl-authentication-error',
  templateUrl: './authentication-error.component.html',
  styleUrls: ['./authentication-error.component.scss'],
})
export class AuthenticationErrorComponent implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.clearSessionStorage();
  }
}
