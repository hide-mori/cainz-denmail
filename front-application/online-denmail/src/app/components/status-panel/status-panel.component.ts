import { Component, OnInit, OnChanges, SimpleChanges, EventEmitter, Input, Output } from '@angular/core';

import {StatusItem} from '../../modal/renrakuData'

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
     // 未読 作業中 作業者完了 作業者対象外 完了          
     this.statusArr = [];
     this.statusArr.push({
       name: "未読",
       name1:"",
       statusId: '1',
       varcolor: "--ion-color-status_unread",
       cnt: 0,
     });
     this.statusArr.push({
       name: "作業中",
       name1:"",
       statusId: '2',
       varcolor: "--ion-color-status_working",
       cnt: 0,
     });
     this.statusArr.push({
       name: "作業者",
       name1:"完了",
       statusId: '3',
       varcolor: "--ion-color-status_worker_completed",
       cnt: 0,
     });
     this.statusArr.push({
       name: "作業者",
       name1: "対象外",
       statusId: '4',
       varcolor: "--ion-color-status_worker_excluded",
       cnt: 0,
     });
     this.statusArr.push({
       name: "対象外",
       name1:"",
       statusId: '5',
       varcolor: "--ion-color-status_excluded",
       cnt: 0,
     });
     this.statusArr.push({
       name: "完了",
       name1:"",
       statusId: '6',
       varcolor: "--ion-color-status_completed",
       cnt: 0,
     });
      }
  }

}
