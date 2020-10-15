package com.tcs.denmail.online.domain.service.shudai;

import java.util.Optional;
import com.tcs.denmail.online.app.model.ShudaiModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.ShudaiEntity;
import com.tcs.denmail.online.domain.repository.ShudaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShudaiServiceImpl implements ShudaiService {

    @Autowired
    private ShudaiRepository shudaiRepository;

    @Transactional
    @Override
    public ShudaiEntity syncData(ShudaiModel in) {

        ReturnValue returnValue = new ReturnValue();
        switch (in.getRealTimeLinkDeleteFlg()) {
            // INSERT_UPDATE
            case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                // DBよりデータを取得(キー値)
                Optional<ShudaiEntity> find = shudaiRepository.findById(in.getShudaiCd());
                find.ifPresentOrElse(entity -> {
                    // UPDATE
                    entity.setShudaiNm(in.getShudaiNm());
                    entity.setDispFlg(in.getDispFlg());
                    returnValue.entity = shudaiRepository.saveAndFlush(entity);
                }, () -> {
                    // INSERT
                    ShudaiEntity entity = new ShudaiEntity();
                    entity.setShudaiCd(in.getShudaiCd());
                    entity.setShudaiNm(in.getShudaiNm());
                    entity.setDispFlg(in.getDispFlg());
                    returnValue.entity = shudaiRepository.saveAndFlush(entity);
                });
                break;
            // DELETE
            case RealTimeLinkDeleteFlg.DELETE:
                shudaiRepository.findById(in.getShudaiCd()).ifPresentOrElse(entity -> {
                    returnValue.entity = entity;
                    shudaiRepository.delete(entity);
                }, () -> {
                    // TODO log
                });
                break;
            default:
                break;
        }

        return returnValue.entity;
    }


    class ReturnValue {
        ShudaiEntity entity = new ShudaiEntity();
    }
}
