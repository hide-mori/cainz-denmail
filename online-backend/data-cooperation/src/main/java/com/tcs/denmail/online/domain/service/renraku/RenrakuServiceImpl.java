package com.tcs.denmail.online.domain.service.renraku;

import java.util.List;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.common.util.LogUtil;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
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
    public CooperationResponseModel syncData(List<RenrakuModel> dataList) {

        CooperationResponseModel returnValue = new CooperationResponseModel();

        for (RenrakuModel data : dataList) {
            // 開始ログ
            logStart(data);

            switch (data.getRealTimeLinkDeleteFlg()) {
                // INSERT_UPDATE
                case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                    // DBよりデータを取得(キー値)
                    renrakuRepository.findById(data.getKanriNo()).ifPresentOrElse(entity -> {
                        BeanUtils.copyProperties(data, entity);
                        renrakuRepository.save(entity);
                        returnValue.updateCount++;
                    }, () -> {
                        RenrakuEntity entity = new RenrakuEntity();
                        BeanUtils.copyProperties(data, entity);
                        renrakuRepository.save(entity);
                        returnValue.insertCount++;
                    });
                    break;
                // DELETE
                case RealTimeLinkDeleteFlg.DELETE:
                    renrakuRepository.findById(data.getKanriNo()).ifPresentOrElse(entity -> {
                        renrakuRepository.delete(entity);
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
