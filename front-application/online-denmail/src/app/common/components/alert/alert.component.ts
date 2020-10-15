import { Component, OnInit } from '@angular/core';
import { AlertService } from '../../services/alert.service';

import { Alert } from '../../model/alert';
import { BaseComponent } from '../base-component';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss'],
})
export class AlertComponent extends BaseComponent implements OnInit {
  constructor(public alertService: AlertService) {
    super();
  }

  ngOnInit(): void {
    this.alertService.alerts = [];
  }

  close(alert: Alert) {
    this.alertService.alerts.splice(this.alertService.alerts.indexOf(alert), 1);
  }

  closeAlert() {
    this.alertService.popAll();
  }
}
