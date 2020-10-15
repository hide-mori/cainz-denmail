import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'mbl-system-error',
  templateUrl: './system-error.component.html',
  styleUrls: ['./system-error.component.scss'],
})
export class SystemErrorComponent implements OnInit {
  private currentUrl: string;
  userType: string;

  constructor() {}

  ngOnInit() {}
}
