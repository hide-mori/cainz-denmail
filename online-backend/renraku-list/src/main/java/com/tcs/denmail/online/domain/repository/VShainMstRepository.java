package com.tcs.denmail.online.domain.repository;

import com.tcs.denmail.common.repository.TcsBaseRepository;
import com.tcs.denmail.online.domain.entity.VShainMstEntity;

/**
 * VShainMstRepository
 */
public interface VShainMstRepository extends TcsBaseRepository<VShainMstEntity, String> {
    
        public VShainMstEntity findByShainCd(String shainCd);
}