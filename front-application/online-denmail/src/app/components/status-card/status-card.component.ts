import { Component, EventEmitter, Input, Output } from '@angular/core';

import {StatusItem} from '../../modal/renrakuData'


@Component({
  selector: 'app-status-card',
  templateUrl: './status-card.component.html',
  styleUrls: ['./status-card.component.scss'],
})
export class StatusCardComponent {

  @Input()
  status :StatusItem;

  @Output()
  result = new EventEmitter<StatusItem>();

  constructor() { }

  refreshList(statusId :Object){
    console.log("refreshList");
    console.log(statusId);

    let newStatusItem : StatusItem = new StatusItem();
    newStatusItem.name = this.status.name + "-n";
    newStatusItem.cnt = this.status.cnt;
    newStatusItem.varcolor = this.status.varcolor;

    this.result.emit(newStatusItem);
  }
}
