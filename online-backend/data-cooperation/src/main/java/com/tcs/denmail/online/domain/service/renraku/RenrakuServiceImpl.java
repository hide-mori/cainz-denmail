package com.tcs.denmail.online.domain.service.renraku;

import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.RenrakuModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.RenrakuEntity;
import com.tcs.denmail.online.domain.repository.RenrakuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RenrakuServiceImpl extends TcsBaseService implements RenrakuService {

    @Autowired
    private RenrakuRepository renrakuRepository;

    @Transactional
    @Override
    public RenrakuEntity syncData(RenrakuModel in) {

        ReturnValue returnValue = new ReturnValue();

        switch (in.getRealTimeLinkDeleteFlg()) {
            // INSERT_UPDATE
            case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                // DBよりデータを取得(キー値)
                renrakuRepository.findById(in.getKanriNo()).ifPresentOrElse(entity -> {
                    BeanUtils.copyProperties(in, entity);
                    returnValue.entity = renrakuRepository.saveAndFlush(entity);
                }, () -> {
                    RenrakuEntity entity = new RenrakuEntity();
                    BeanUtils.copyProperties(in, entity);
                    returnValue.entity = renrakuRepository.saveAndFlush(entity);
                });
                break;
            // DELETE
            case RealTimeLinkDeleteFlg.DELETE:
                renrakuRepository.findById(in.getKanriNo()).ifPresentOrElse(entity -> {
                    returnValue.entity = entity;
                    renrakuRepository.delete(entity);
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
        RenrakuEntity entity = new RenrakuEntity();
    }

}
