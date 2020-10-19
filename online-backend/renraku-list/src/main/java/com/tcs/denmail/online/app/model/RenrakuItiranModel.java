package com.tcs.denmail.online.app.model;

import java.util.List;

import lombok.Data;

@Data
public class RenrakuItiranModel {

    List<RenrakuItiranItemModel> itemList;

    int unread =  0;
    //作業中 
    int workingCnt = 0;

    // 作業者完了
    int workerCompleted = 0;

    // 作業者対象外
    int workerExcluded = 0;
    
    // 対象外
    int excluded = 0;
    // 完了
    int completed = 0;

    String errorCode ="";
    String errorMsg = "";

}
