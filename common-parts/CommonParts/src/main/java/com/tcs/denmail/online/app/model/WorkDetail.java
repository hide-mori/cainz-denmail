package com.tcs.denmail.online.app.model;

import lombok.Data;

@Data
public class WorkDetail {

    private String workDetailId;
    private String workDetailName;
    private Integer workDetailStatus;
    private String workDetailStatusString;
    private String employeeId;
    private String employeeName;
    private String workDetailContent;
    private String workDetailProcess;
    private String filePath;
    private String report;
    private Boolean completed;

}
