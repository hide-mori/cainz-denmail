package com.tcs.denmail.online.domain.repository;

import java.util.List;

import com.tcs.denmail.common.repository.TcsBaseRepository;
import com.tcs.denmail.online.domain.entity.WorkDetailEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkDetailRepository extends TcsBaseRepository<WorkDetailEntity, WorkDetailEntity.WorkDetailEntityPk> {

    List<WorkDetailEntity> findByWorkDetailStatus(Integer status);

    @Query("select d from WorkDetailEntity d where d.workDetailStatus = :status")
    List<WorkDetailEntity> findByWorkDetailStatusCustmize(@Param("status") Integer status);
}
