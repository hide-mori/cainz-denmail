package com.tcs.denmail.online.domain.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_work")
public class WorkEntity {

    @Id
    private Integer workId;
    private String workName;
    private LocalDate assigningDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer workStatus;
    private String tenpoId;
    private String tenpoName;

    @OneToMany
    @JoinColumn(name = "workId")
    private List<WorkDetailEntity> workDetailEntities;

}
