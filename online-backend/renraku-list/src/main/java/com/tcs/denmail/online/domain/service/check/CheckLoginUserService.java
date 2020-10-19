package com.tcs.denmail.online.domain.service.check;

import com.tcs.denmail.online.app.model.LoginUserModel;

public interface CheckLoginUserService {

    LoginUserModel getLoginUserModel(String shainCd);
    
}
