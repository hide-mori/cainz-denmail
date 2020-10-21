export class AttachmentFile {
  key: string;
  fileName: string;
  viewable: boolean;
  s3FileExists: boolean = true;
  constructor(key: string, fileName: string, viewable: boolean, s3FileExistsIn = true) {
    this.key = key;
    this.fileName = fileName;
    this.viewable = viewable;
    this.s3FileExists = s3FileExistsIn;
  }
}​​