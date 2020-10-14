package com.tcs.denmail.online.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.tcs.denmail.common.repository.TcsBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_work_detail")
@IdClass(WorkDetailEntityPk.class)
public class WorkDetailEntity extends TcsBaseEntity {

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

}

@Data
class WorkDetailEntityPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer workId;
    private Integer workDetailId;
}
