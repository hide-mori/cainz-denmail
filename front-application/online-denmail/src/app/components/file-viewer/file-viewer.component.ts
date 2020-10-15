import { Component, Input, OnInit } from '@angular/core';
import { FileServiceService } from '../../services/file-service.service';
import { ModalController } from '@ionic/angular';
import { CommonModule } from '@angular/common';

import { AttachmentFile } from "../../modal/AttachmentFile";

@Component({
  selector: 'app-file-viewer',
  templateUrl: './file-viewer.component.html',
  styleUrls: ['./file-viewer.component.scss'],
})
export class FileViewerComponent implements OnInit {
  @Input() file: AttachmentFile;

  fileSrc: Uint8Array;

  constructor(private fileServiceService: FileServiceService, public modalController: ModalController) {
    (window as any).pdfWorkerSrc = '/assets/pdf.worker.min.js';
  }

  ngOnInit() {
    this.fileServiceService.getS3File(this.file.key).then((res) => {
      this.fileSrc = res.Body as Uint8Array;
    });
  }

  dismiss() {
    // using the injected ModalController this page
    // can "dismiss" itself and optionally pass back data
    this.fileSrc = null;
    this.modalController.dismiss({
      dismissed: true,
    });
  }
}
