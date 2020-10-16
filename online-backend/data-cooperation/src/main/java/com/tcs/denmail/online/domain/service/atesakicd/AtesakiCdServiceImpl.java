package com.tcs.denmail.online.domain.service.atesakicd;

import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.AtesakiCdModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.AtesakiCdEntity;
import com.tcs.denmail.online.domain.repository.AtesakiCdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtesakiCdServiceImpl extends TcsBaseService implements AtesakiCdService {

    @Autowired
    private AtesakiCdRepository atesakiCdRepository;

    @Transactional
    @Override
    public AtesakiCdEntity syncData(AtesakiCdModel in) {
        ReturnValue returnValue = new ReturnValue();

        AtesakiCdEntity atesakiCdEntity = new AtesakiCdEntity();
        atesakiCdEntity.setKanriNo(in.getKanriNo());
        atesakiCdEntity.setTaishoKbn(in.getTaishoKbn());
        atesakiCdEntity.setCAtesakiCd(in.getCAtesakiCd());
        atesakiCdEntity.setRAtesakiCd(in.getRAtesakiCd());

        switch (in.getRealTimeLinkDeleteFlg()) {
            // INSERT_UPDATE
            case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                // DBよりデータを取得(キー値)
                if (atesakiCdRepository.findById(atesakiCdEntity).isPresent()) {
                    // TODO log
                } else {
                    returnValue.entity = atesakiCdRepository.saveAndFlush(atesakiCdEntity);
                }
                break;
            // DELETE
            case RealTimeLinkDeleteFlg.DELETE:
                atesakiCdRepository.findById(atesakiCdEntity).ifPresentOrElse(entity -> {
                    returnValue.entity = entity;
                    atesakiCdRepository.delete(entity);
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
