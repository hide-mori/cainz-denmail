import { Component, OnInit, OnChanges, SimpleChanges, EventEmitter, Input, Output } from '@angular/core';

import {StatusItem} from '../../modal/renrakuData'
import {StatusUtility} from '../../utility/status-utility'

@Component({
  selector: 'app-status-panel',
  templateUrl: './status-panel.component.html',
  styleUrls: ['./status-panel.component.scss'],
})
export class StatusPanelComponent implements OnInit, OnChanges {

   constructor() { }

  @Input()
  statusArr :StatusItem[];

  @Output()
  result = new EventEmitter<StatusItem>();

  selectOneStatusItem(eventObject :Object, selectedStatusItem: StatusItem) {

    let newStatusItem : StatusItem = new StatusItem();
    newStatusItem.name = selectedStatusItem.name;
    newStatusItem.statusId = selectedStatusItem.statusId;
    newStatusItem.varcolor = selectedStatusItem.varcolor;
    newStatusItem.cnt = selectedStatusItem.cnt;
    
    this.result.emit(newStatusItem);
  }

  
  refreshList(statusId :Object, a: object){
    console.log("refreshList ");
    console.log(statusId);
  }


  ngOnChanges( changes: SimpleChanges){
    console.log(changes);
 }
  ngOnInit() {
          
      if(this.statusArr == null || this.statusArr.length==0) {
        this.statusArr = StatusUtility.getStatusArr();
      }

      /*
      const el = document.querySelector('.status-card');
      console.log(el);
      */
  }
}
