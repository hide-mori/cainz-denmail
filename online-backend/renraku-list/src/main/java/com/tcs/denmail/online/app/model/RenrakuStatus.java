package com.tcs.denmail.online.app.model;

public enum RenrakuStatus {

    UNREAD("未読", 1),
    WORKING("作業中",2),
    WORKER_COMPLETED("作業者完了",3),
    WORKER_EXCLUDED("作業者対象外",4),
    EXCLUDED("対象外",5),
    COMPLETED("完了", 6);

    private String label;
    private int id;

    private RenrakuStatus(String label, int id) {   //コンストラクタはprivateで宣言
        this.label = label;
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public int getId() {
        return id;
    }
}
