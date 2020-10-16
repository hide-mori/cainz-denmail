package com.tcs.denmail.online.domain.service.vatesaki;

import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.VAtesakiModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.VAtesakiEntity;
import com.tcs.denmail.online.domain.repository.VAtesakiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VAtesakiServiceImpl extends TcsBaseService implements VAtesakiService {

    @Autowired
    private VAtesakiRepository repository;

    @Transactional
    @Override
    public VAtesakiEntity syncData(VAtesakiModel in) {
        ReturnValue returnValue = new ReturnValue();

        switch (in.getRealTimeLinkDeleteFlg()) {
            // INSERT_UPDATE
            case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                // DBよりデータを取得(キー値)
                repository.findById(in.getCrAtesakiCd()).ifPresentOrElse(entity -> {
                    entity.setCrAtesakiNm(in.getCrAtesakiNm());
                    entity.setCAtesakiCd(in.getCAtesakiCd());
                    entity.setRAtesakiCd(in.getRAtesakiCd());
                    entity.setCSort(in.getCSort());
                    entity.setRSort(in.getRSort());
                    returnValue.entity = repository.saveAndFlush(entity);
                }, () -> {
                    VAtesakiEntity entity = new VAtesakiEntity();
                    entity.setCrAtesakiCd(in.getCrAtesakiCd());
                    entity.setCrAtesakiNm(in.getCrAtesakiNm());
                    entity.setCAtesakiCd(in.getCAtesakiCd());
                    entity.setRAtesakiCd(in.getRAtesakiCd());
                    entity.setCSort(in.getCSort());
                    entity.setRSort(in.getRSort());
                    returnValue.entity = repository.saveAndFlush(entity);
                });
                break;
            // DELETE
            case RealTimeLinkDeleteFlg.DELETE:
                repository.findById(in.getCrAtesakiCd()).ifPresentOrElse(entity -> {
                    returnValue.entity = entity;
                    repository.delete(entity);
                }, () -> {
                    // TODO log
                });
                break;
            default:
                // TODO log
                break;
        }

        return returnValue.entity;
    }

    class ReturnValue {
        VAtesakiEntity entity = new VAtesakiEntity();
    }

}
