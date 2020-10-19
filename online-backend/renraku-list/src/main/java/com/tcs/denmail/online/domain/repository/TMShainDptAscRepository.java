package com.tcs.denmail.online.domain.repository;

import java.util.List;
import com.tcs.denmail.common.repository.TcsBaseRepository;
import com.tcs.denmail.online.domain.entity.TMShainDptAscEntity;

/**
 * TMShainDptAscRepository
 */
public interface TMShainDptAscRepository extends TcsBaseRepository<TMShainDptAscEntity, String> {
    public List<TMShainDptAscEntity> findByShainCd(String shainCd);
}
