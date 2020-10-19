package com.tcs.denmail.online.domain.service.check;

import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.LoginUserModel;
import com.tcs.denmail.online.domain.entity.VShainMstEntity;
import com.tcs.denmail.online.domain.repository.VShainMstRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckLoginUserServiceImpl extends TcsBaseService implements CheckLoginUserService{

        @Autowired
        private VShainMstRepository vShainMstRepository;

        public LoginUserModel getLoginUserModel(String shainCd){

            LoginUserModel model = new LoginUserModel();
            VShainMstEntity entity = vShainMstRepository.findByShainCd(shainCd);
            if(entity!= null){
            model.setShainCd(entity.getShainCd());
            model.setUserName(entity.getShainNm());
            model.setTenpoCd(entity.getTenCd());
            model.setTenpoName(entity.getTenRyakuMeiKj());
            model.setUserType(entity.getDmlShokuiCd());
            }

            return model;
        }
 
}
