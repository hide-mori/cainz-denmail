package com.tcs.denmail.online.domain.service;

import java.util.List;
import com.tcs.denmail.online.app.model.CooperationResponseModel;

public interface DataCooperationService<T> {

    CooperationResponseModel syncData(List<T> dataList);

}
