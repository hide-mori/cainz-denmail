package com.tcs.denmail.online.domain.service.atesakicd;

import com.tcs.denmail.online.app.model.AtesakiCdModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.AtesakiCdEntity;
import com.tcs.denmail.online.domain.repository.AtesakiCdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtesakiCdServiceImpl implements AtesakiCdService {

    @Autowired
    private AtesakiCdRepository atesakiCdRepository;

    @Transactional
    @Override
    public AtesakiCdEntity syncData(AtesakiCdModel in) {
        ReturnValue returnValue = new ReturnValue();

        AtesakiCdEntity entity = new AtesakiCdEntity();
        entity.setKanriNo(in.getKanriNo());
        entity.setTaishoKbn(in.getTaishoKbn());
        entity.setCAtesakiCd(in.getCAtesakiCd());
        entity.setRAtesakiCd(in.getRAtesakiCd());

        switch (in.getRealTimeLinkDeleteFlg()) {
            // INSERT_UPDATE
            case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                // DBよりデータを取得(キー値)
                if (atesakiCdRepository.findById(entity).isPresent()) {
                    // TODO log
                } else {
                    returnValue.entity = atesakiCdRepository.saveAndFlush(entity);
                }
                break;
            // DELETE
            case RealTimeLinkDeleteFlg.DELETE:
                atesakiCdRepository.findById(entity).ifPresentOrElse(e -> {
                    returnValue.entity = e;
                    atesakiCdRepository.delete(e);
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
        AtesakiCdEntity entity = new AtesakiCdEntity();
    }
}
