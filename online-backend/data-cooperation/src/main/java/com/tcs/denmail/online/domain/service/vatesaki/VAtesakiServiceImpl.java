package com.tcs.denmail.online.domain.service.vatesaki;

import java.util.List;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.common.util.LogUtil;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
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
    public CooperationResponseModel syncData(List<VAtesakiModel> dataList) {

        CooperationResponseModel returnValue = new CooperationResponseModel();

        for (VAtesakiModel data : dataList) {

            switch (data.getRealTimeLinkDeleteFlg()) {
                // INSERT_UPDATE
                case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                    // DBよりデータを取得(キー値)
                    repository.findById(data.getCrAtesakiCd()).ifPresentOrElse(entity -> {
                        entity.setCrAtesakiNm(data.getCrAtesakiNm());
                        entity.setCAtesakiCd(data.getCAtesakiCd());
                        entity.setRAtesakiCd(data.getRAtesakiCd());
                        entity.setCSort(data.getCSort());
                        entity.setRSort(data.getRSort());
                        repository.save(entity);
                        returnValue.updateCount++;
                    }, () -> {
                        VAtesakiEntity entity = new VAtesakiEntity();
                        entity.setCrAtesakiCd(data.getCrAtesakiCd());
                        entity.setCrAtesakiNm(data.getCrAtesakiNm());
                        entity.setCAtesakiCd(data.getCAtesakiCd());
                        entity.setRAtesakiCd(data.getRAtesakiCd());
                        entity.setCSort(data.getCSort());
                        entity.setRSort(data.getRSort());
                        repository.save(entity);
                        returnValue.insertCount++;
                    });
                    break;
                // DELETE
                case RealTimeLinkDeleteFlg.DELETE:
                    repository.findById(data.getCrAtesakiCd()).ifPresentOrElse(entity -> {
                        repository.delete(entity);
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
        }

        return returnValue;
    }

}
