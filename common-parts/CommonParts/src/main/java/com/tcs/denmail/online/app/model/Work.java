package com.tcs.denmail.online.app.model;

import java.util.List;

import lombok.Data;

@Data
public class Work {

    private String workId;
    private String workName;
    private String assigningDate;
    private String deadline;
    private Integer workStatus;
    private String tenpoId;
    private String tenpoName;
    private List<WorkDetail> workDetails;

}
