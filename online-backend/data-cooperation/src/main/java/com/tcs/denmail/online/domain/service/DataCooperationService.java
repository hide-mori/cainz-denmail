package com.tcs.denmail.online.domain.service;

import java.util.List;
import com.tcs.denmail.common.util.LogUtil;
import com.tcs.denmail.online.app.model.CooperationResponseModel;

public interface DataCooperationService<T> {

    CooperationResponseModel syncData(List<T> dataList);

    default void logStart(T data) {
        LogUtil.log("DM0001I", data.toString());
    }

    default void logEnd(T data) {
        LogUtil.log("DM0002I", data.toString());
    }
}
