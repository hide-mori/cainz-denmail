package com.tcs.denmail.online.domain.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tcs.denmail.common.repository.TcsBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_work")
public class WorkEntity extends TcsBaseEntity {

    @Id
    private Integer workId;
    private String workName;
    private LocalDate assigningDate;
    private LocalDate deadline;
    private Integer workStatus;
    private String tenpoId;
    private String tenpoName;

    @OneToMany
    @JoinColumn(name = "workId")
    private List<WorkDetailEntity> workDetailEntities;

}
