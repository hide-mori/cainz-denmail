export class RenrakuBunshouData {
    // 管理番号
    kannriNo: string;
    // 作業開始日
    sagyoFromYmd: string;
    // 作業終了日
    sagyoToYmd: string;
    // 主題コード
    shudaiCd: string;
    // 主題名
    shudaiNm: string;
    // 品番
    hinban: string;
    // 件名
    kenmei: string;
    dspKenmei: string;
    // 1~6
    statusId: string;
    // 未読 作業中 作業者完了 作業者対象外 完了
    statusNm:string;
}
export class ShainInfoData {
    // 店舗コード
    tenCd: string;
    // 店舗名_略称
    tenRyakuMeiKj: string;
    // 社員コード
    shainCd: string;
    // 社員名
    shainNam: string;
}
export class StatusItem { 
    // 未読 作業中 作業者完了 作業者対象外 完了
    name: string;
    name1: string;
    statusId: string;
    varcolor: string;
    cnt: number 
}

