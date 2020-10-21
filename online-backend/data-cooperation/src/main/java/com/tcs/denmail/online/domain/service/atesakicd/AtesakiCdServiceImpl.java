package com.tcs.denmail.online.domain.service.atesakicd;

import java.util.List;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.common.util.LogUtil;
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

        for (AtesakiCdModel data : inList) {
            // 開始ログ
            logStart(data);

            AtesakiCdEntity atesakiCdEntity = new AtesakiCdEntity();
            atesakiCdEntity.setKanriNo(data.getKanriNo());
            atesakiCdEntity.setTaishoKbn(data.getTaishoKbn());
            atesakiCdEntity.setCAtesakiCd(data.getCAtesakiCd());
            atesakiCdEntity.setRAtesakiCd(data.getRAtesakiCd());

            switch (data.getRealTimeLinkDeleteFlg()) {
                // INSERT_UPDATE
                case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                    // DBよりデータを取得(キー値)
                    // DBに存在しない場合、登録する
                    if (!atesakiCdRepository.findById(atesakiCdEntity).isPresent()) {
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
                        // 例外 存在しない
                        LogUtil.log("DM0001W", data.toString());
                    });
                    break;
                default:
                    LogUtil.log("DM0002W", data.toString());
                    break;
            }
            // 終了ログ
            logEnd(data);
        }
        return returnValue;
    }

}
