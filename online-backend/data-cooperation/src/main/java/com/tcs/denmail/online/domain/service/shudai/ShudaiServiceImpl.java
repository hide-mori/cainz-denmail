package com.tcs.denmail.online.domain.service.shudai;

import java.util.List;
import java.util.Optional;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.common.util.LogUtil;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.ShudaiModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.ShudaiEntity;
import com.tcs.denmail.online.domain.repository.ShudaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShudaiServiceImpl extends TcsBaseService implements ShudaiService {

    @Autowired
    private ShudaiRepository shudaiRepository;

    @Transactional
    @Override
    public CooperationResponseModel syncData(List<ShudaiModel> dataList) {

        CooperationResponseModel returnValue = new CooperationResponseModel();

        for (ShudaiModel data : dataList) {
            // 開始ログ
            logStart(data);

            switch (data.getRealTimeLinkDeleteFlg()) {
                // INSERT_UPDATE
                case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                    // DBよりデータを取得(キー値)
                    Optional<ShudaiEntity> find = shudaiRepository.findById(data.getShudaiCd());
                    find.ifPresentOrElse(entity -> {
                        // UPDATE
                        entity.setShudaiNm(data.getShudaiNm());
                        entity.setDispFlg(data.getDispFlg());
                        shudaiRepository.save(entity);
                        returnValue.updateCount++;
                    }, () -> {
                        // INSERT
                        ShudaiEntity entity = new ShudaiEntity();
                        entity.setShudaiCd(data.getShudaiCd());
                        entity.setShudaiNm(data.getShudaiNm());
                        entity.setDispFlg(data.getDispFlg());
                        shudaiRepository.save(entity);
                        returnValue.insertCount++;
                    });
                    break;
                // DELETE
                case RealTimeLinkDeleteFlg.DELETE:
                    shudaiRepository.findById(data.getShudaiCd()).ifPresentOrElse(entity -> {
                        shudaiRepository.delete(entity);
                        returnValue.deleteCount++;
                    }, () -> {
                        // 存在しない
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
