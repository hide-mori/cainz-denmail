package com.tcs.denmail.online.domain.service;

import com.tcs.denmail.common.exception.TcsApplicationException;
import com.tcs.denmail.online.app.model.ShainDptModel;
import com.tcs.denmail.online.domain.entity.ShainDptEntity;

import java.util.List;

/**
 * WorkService
 */
public interface ShainDptService {

    List<ShainDptEntity> getShainDpt(List<ShainDptModel> shainDptList)
            throws TcsApplicationException;

}
