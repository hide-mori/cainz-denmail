export class AttachmentFile {
  key: string;
  fileName: string;
  viewable: boolean;
  constructor(key: string, fileName: string, viewable: boolean) {
    this.key = key;
    this.fileName = fileName;
    this.viewable = viewable;
  }
}​​