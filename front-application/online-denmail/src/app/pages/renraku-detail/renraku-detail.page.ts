import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ActivatedRouteSnapshot } from "@angular/router";

import { Observable } from 'rxjs';

import { StatusItem } from "../../modal/renrakuData";
import { StatusUtility } from "../../utility/status-utility";
import { AuthService } from "../../common/services/auth.service";
import { Login } from "../../common/model/login";
import { environment } from '../../../environments/environment';

import { AttachmentFile } from "../../modal/AttachmentFile";
import { ShainInfoData, RenrakuBunshouData } from "../../modal/renrakuData";
import { RenrakuDetailData } from "../../modal/renrakuDetailData";
import { FileServiceService } from "../../services/file-service.service";
/* import { S3FileGetService } from '../../services/S3FileGetService.service'; */

import { HttpClient } from "@angular/common/http";

import { AWSError } from "aws-sdk";
import { ModalController } from "@ionic/angular";
import { FileViewerComponent } from "../../components/file-viewer/file-viewer.component";

@Component({
  selector: "app-renraku-detail",
  templateUrl: "./renraku-detail.page.html",
  styleUrls: ["./renraku-detail.page.scss"],
})
export class RenrakuDetailPage implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private fileServiceService: FileServiceService,
    public modalController: ModalController,
    private http: HttpClient,
    private authService: AuthService
  ) { }

  loginInfoData: Login;
  attachmentFiles: AttachmentFile[] = [];
  statusName: string = "";

  ngOnInit() {
    this.data = new RenrakuDetailData();
    this.loginInfoData = new Login();

    let kanriNo = "R16102841";
    let tenpoCd = "0284";
    let statusId = "";

    this.loginInfoData = this.authService.getSession();
    tenpoCd = this.loginInfoData.tenpoCd;

    console.log("RenrakuDetailPage  ngOnInit;;;;;;;;;;;;;;;;");
    console.log(this.loginInfoData);

    let sub = this.route.params.subscribe(params => {
      console.log("params['kanrino']=" + params['kanrino']);
      kanriNo = params['kanrino'];

      this.statusName = StatusUtility.getStatusName(params['statusId']);

      this.doSearchAction(kanriNo, tenpoCd)
        .subscribe(
          (res) => {
          console.log(res);
          this.data = res;
          this.getS3FileList(res.kanriNo, res.attachmentFiles);
        });
    });
  }

  getS3FileList(kanriNo: string, dbFiles: string[]) {

    let setPageAttachmentFiles = function (fileArr: AttachmentFile[],
      dbAttachmentFiles: string[],
      s3AttachmentFiles: AttachmentFile[]) {

      fileArr.splice(0);

      dbAttachmentFiles.forEach(
        dbFileName => {
          let okArr: AttachmentFile[] = s3AttachmentFiles.filter(tmpF => tmpF.fileName == dbFileName);
          if (okArr.length > 0) {
            fileArr.push(okArr[0]);
          } else {
            fileArr.push(new AttachmentFile("", dbFileName, false, false));
          }
        });

      //Test code
      s3AttachmentFiles.forEach(
        s3File => {
          fileArr.push(s3File);
        }
      )
      //Test code
    };

    // TODO: "files" ⇒　kanriNo;
    this.fileServiceService.getS3FileList("files").then((res) => {
      console.log(res);
      if (res.$response.error) {
        console.error(res.$response.error);
      } else {
        let s3Files: AttachmentFile[] = res.Contents.map((s3object) => {
          const objectKey = s3object.Key;
          const splitKey = objectKey.split("/");
          return new AttachmentFile(
            objectKey,
            splitKey[1],
            objectKey.endsWith(".pdf")
          );
        });

        setPageAttachmentFiles(this.attachmentFiles, dbFiles, s3Files);
      }
    });
  }

  async viewFile(attachmentFile: AttachmentFile) {
    const modal = await this.modalController.create({
      component: FileViewerComponent,
      animated: false,
      componentProps: {
        file: attachmentFile,
      },
    });
    return await modal.present();
  }

  data: RenrakuDetailData;

  urlPathString: string = environment.API_URL_RENRAKU_DETAIL;

  urlPathString_2: string = "";
　
  doSearchAction(kanriNo: string, tenpoCd: string): Observable<RenrakuDetailData> {
    console.log("doSearchAction= kanriNo=" + kanriNo + ";tenpoCd=" + tenpoCd);

    return this.http
      .get<RenrakuDetailData>(this.urlPathString, {
        params: {
          kanriNo: kanriNo,
          atesakiTenpoCd: tenpoCd,
        },
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      });
  }
}
