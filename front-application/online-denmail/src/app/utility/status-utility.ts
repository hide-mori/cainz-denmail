import { StatusItem } from "../modal/renrakuData";

/**
 * 連絡文書進捗状況のステータス
 */
export class StatusUtility {
    public static getStatusArr(): StatusItem[] {
        // 未読 作業中 作業者完了 作業者対象外 完了          
        let statusArr: StatusItem[] = [];
        statusArr.push({
            name: "未読",
            name1: "　　",
            statusId: '1',
            varcolor: "--ion-color-status_unread",
            cnt: 0,
        });
        statusArr.push({
            name: "作業中",
            name1: "　　",
            statusId: '2',
            varcolor: "--ion-color-status_working",
            cnt: 0,
        });
        statusArr.push({
            name: "作業者",
            name1: "完了",
            statusId: '3',
            varcolor: "--ion-color-status_worker_completed",
            cnt: 0,
        });
        statusArr.push({
            name: "作業者",
            name1: "対象外",
            statusId: '4',
            varcolor: "--ion-color-status_worker_excluded",
            cnt: 0,
        });
        statusArr.push({
            name: "対象外",
            name1: "　　",
            statusId: '5',
            varcolor: "--ion-color-status_excluded",
            cnt: 0,
        });
        statusArr.push({
            name: "完了",
            name1: "　　",
            statusId: '6',
            varcolor: "--ion-color-status_completed",
            cnt: 0,
        });

        return statusArr;
    }

    public static setStatusItemCnt(data: any, statusArr: StatusItem[]) {

        statusArr.forEach(tmp => {
          switch (tmp.statusId) {
            case '1': tmp.cnt = parseInt(data.unread); break;
            case '2': tmp.cnt = parseInt(data.workingCnt); break;
            case '3': tmp.cnt = parseInt(data.workerCompleted); break;
            case '4': tmp.cnt = parseInt(data.workerExcluded); break;
            case '5': tmp.cnt = parseInt(data.completed); break;
            case '6': tmp.cnt = parseInt(data.excluded); break;
          }
        });    
      }
    // background: var(--ion-color-status_unread)
    public static statusBgColorDic: { [index: string]: string } = {
        "1": "--ion-color-status_unread",
        "2": "--ion-color-status_working",
        "3": "--ion-color-status_worker_completed",
        "4": "--ion-color-status_worker_excluded",
        "5": "--ion-color-status_excluded",
        "6": "--ion-color-status_completed",
    };

    public static getStatusName(statusId: string): string{

        let arr:StatusItem[] = StatusUtility.getStatusArr(); 

        let tmp :StatusItem[] = arr.filter( param =>{
          return (param.statusId == statusId); 
        });
        return (tmp != null && tmp.length > 0) ?
                 (tmp[0].name + tmp[0].name1).trim(): "";
    }
}