package com.tcs.denmail.online.domain.service.renraku;

import com.tcs.denmail.online.app.model.RenrakuDetailModel;


/**
 * RenrakuDetailService
 */
public interface RenrakuDetailService {

    RenrakuDetailModel getRenrakuDetail(String kanriNo, String atesakiTenpoCd);

}