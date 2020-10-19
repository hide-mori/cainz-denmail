package com.tcs.denmail.online.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_work_detail")
@IdClass(WorkDetailEntity.WorkDetailEntityPk.class)
public class WorkDetailEntity {

    @Id
    private Integer workId;
    @Id
    private Integer workDetailId;

    private String workDetailName;
    private Integer workDetailStatus;
    private String employeeId;
    private String employeeName;
    private String workDetailContent;
    private String storeWork;
    private String workDetailProcess;
    private String otherInfo;
    private String filePath;
    private String report;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WorkDetailEntityPk implements Serializable {
        private static final long serialVersionUID = 1L;
        private Integer workId;
        private Integer workDetailId;
    }
}
