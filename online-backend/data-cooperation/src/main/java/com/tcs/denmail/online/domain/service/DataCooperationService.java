package com.tcs.denmail.online.domain.service;

import com.tcs.denmail.common.entity.TcsBaseEntity;

public interface DataCooperationService<I, O extends TcsBaseEntity> {

    O syncData(I in);

}
