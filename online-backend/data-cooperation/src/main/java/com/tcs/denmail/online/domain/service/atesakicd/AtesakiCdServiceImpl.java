package com.tcs.denmail.online.domain.service.atesakicd;

import java.util.List;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.AtesakiCdModel;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
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
    public CooperationResponseModel syncData(List<AtesakiCdModel> inList) {
        CooperationResponseModel returnValue = new CooperationResponseModel();

        for (AtesakiCdModel in : inList) {
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
                        atesakiCdRepository.save(atesakiCdEntity);
                        returnValue.insertCount++;
                    }
                    break;
                // DELETE
                case RealTimeLinkDeleteFlg.DELETE:
                    atesakiCdRepository.findById(atesakiCdEntity).ifPresentOrElse(entity -> {
                        atesakiCdRepository.delete(entity);
                        returnValue.deleteCount++;
                    }, () -> {
                        // TODO log
                    });
                    break;
                default:
                    break;
            }
        }
        return returnValue;
    }

}
