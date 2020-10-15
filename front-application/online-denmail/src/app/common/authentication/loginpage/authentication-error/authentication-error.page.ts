import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, ActivatedRouteSnapshot } from "@angular/router";
@Component({
  selector: 'app-authentication-error',
  templateUrl: './authentication-error.page.html',
  styleUrls: ['./authentication-error.page.scss'],
})
export class AuthenticationErrorPage implements OnInit {

  constructor(private activatedRoute:ActivatedRoute,) { }

  errorMessage : string;
  ngOnInit() {
    let msg = this.activatedRoute.snapshot.paramMap.get("msg");
    this.errorMessage = msg;
  }

}
