import {
  Component,
  OnInit,
  OnChanges,
  SimpleChanges,
  Input,
} from "@angular/core";

import { ShainInfoData, RenrakuBunshouData } from "../../modal/renrakuData";

import { HttpClient } from "@angular/common/http";

import { StatusItem } from "../../modal/renrakuData";
import { StatusUtility } from "../../utility/status-utility";
import { AuthService } from "../../common/services/auth.service";
import { Login } from "../../common/model/login";

import { Router, ActivatedRoute, ActivatedRouteSnapshot } from "@angular/router";

export class WorkList {
  itemList: RenrakuBunshouData[];
  unread: string;
  workingCnt: string;
  workerCompleted: string;
  workerExcluded: string;
  excluded: string;
  completed: string;
  errorCode: string;
  errorMsg: string;
}
@Component({
  selector: "app-renraku-list",
  templateUrl: "./renraku-list.page.html",
  styleUrls: ["./renraku-list.page.scss"],
})
export class RenrakuListPage implements OnInit, OnChanges {
  statusArr: StatusItem[];

  // shainCd: string = "01037233";
  shainCd: string = "10147718";
  loginInfoData: Login;

  // urlPathString: string = "assets/apidata/workList.json";
  urlPathString: string = "http://localhost:8080/renrakulist";

  workList: RenrakuBunshouData[];

  constructor(private http: HttpClient,
    private router: Router, private activatedRoute:ActivatedRoute, 
    private authService: AuthService) {}

  ngOnInit() {
    this.statusArr = StatusUtility.getStatusArr();
    this.loginInfoData = new Login();

    let queryParamMapId = this.activatedRoute.snapshot.queryParamMap.get("id");
    console.log(" RenrakuListPage....queryParamMap Id=" + queryParamMapId);
    
    let sessionLoginInfoData: Login = this.authService.getSession();
    if(sessionLoginInfoData == null || sessionLoginInfoData.shainCd == null){
      //this.router.navigate(['login/session-timeout']);
      this.shainCd = queryParamMapId;
    } else {
      this.loginInfoData = sessionLoginInfoData;
      this.shainCd = this.loginInfoData.shainCd;
    }

    if(this.shainCd == null) {
      //shainCd: string = "01037233";
      //let shainCd: string = "10147718";
      console.log(" RanrakuListPage....session is null  ");
      this.shainCd = "10147718";
    } else {
      console.log(" RanrakuListPage....session value=" + this.shainCd);
    }

    // 未読:1; 作業中:2; 作業者完了:3; 作業者対象外:4; 完了:5; 対象外:6;
    this.doSearchAction(["1", "2"]);
  }

  @Input() selectedStatusItem: StatusItem;
  ngOnChanges(changes: SimpleChanges) {
    console.log("RanrakuListPage.ngOnChanges()");
    console.log(changes);

    let seletedStatusId: any;
    for (let propName in changes) {
      let change = changes[propName];
      let curVal = JSON.stringify(change.currentValue);
      let prevVal = JSON.stringify(change.previousValue);

      console.log(propName);
      console.log(curVal);
      console.log(prevVal);

      if (propName === "statusId" && change) {
        seletedStatusId = change;
        break;
      }
    }

    this.doSomething(seletedStatusId);
  }

  private doSomething(input: string) {
    //let newList = this.workList.filter(function (element, index, array) {
    //  return element.statusNm === input;
    //});
    //*/

    this.doSearchAction([input]);
  }

  presentFilter() { }

  async doSearchAction(targetStatusArray: string[]) {
    console.log("doSearchAction= shainCd=" + this.shainCd);

    this.workList = [];

    this.http
      .get<WorkList>(this.urlPathString, {
        params: {
          shainCd: this.shainCd,
        },
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      })
      .subscribe((res) => {
        console.log(res);

        // 各種件数設定
        StatusUtility.setStatusItemCnt(res, this.statusArr);

        this.workList = [];

        res.itemList.forEach((work) => {
          if (targetStatusArray.includes(work.statusId)) {
            let tmp: any = work;

            tmp.statusBgVarColor = StatusUtility.statusBgColorDic[work.statusId];

            console.log(
              "this.statusBgColorDic[work.statusId]  tmp.statusBgVarColor=" +
              tmp.statusBgVarColor
            );
            console.log(tmp);

            this.workList.push(tmp);
          }
        });
      });
  }
  getStatusBgColor(data: RenrakuBunshouData): string {
    return StatusUtility.statusBgColorDic[data.statusId];
  }
  goToDetail(kanriNo: string) {
    this.router.navigate(['/ranraku-detail', kanriNo]);
  }
}
