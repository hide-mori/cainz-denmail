package com.tcs.denmail.online.domain.service.shaindpt;

import java.util.List;
import java.util.Optional;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.common.util.LogUtil;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.ShainDptModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.ShainDptEntity;
import com.tcs.denmail.online.domain.entity.ShainDptEntity.ShainDptEntityPk;
import com.tcs.denmail.online.domain.repository.ShainDptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShainDptServiceImpl extends TcsBaseService implements ShainDptService {

    @Autowired
    private ShainDptRepository shainDptRepository;

    @Transactional
    @Override
    public CooperationResponseModel syncData(List<ShainDptModel> dataList) {

        CooperationResponseModel returnValue = new CooperationResponseModel();

        for (ShainDptModel data : dataList) {

            // 受信データ⇒Entity
            ShainDptEntity shainDptEntity = new ShainDptEntity();
            shainDptEntity.setShainCd(data.getShainCd());
            shainDptEntity.setDptCd(data.getDptCd());
            shainDptEntity.setTenpoCd(data.getTenpoCd());

            // System.out.println(shainDptEntity.toString());
            ShainDptEntityPk shainDptEntityPk =
                    new ShainDptEntityPk(data.getShainCd(), data.getDptCd());

            // DBよりデータを取得(キー値)
            Optional<ShainDptEntity> shainDptOpt = shainDptRepository.findById(shainDptEntityPk);
            if (shainDptOpt.isPresent()) {
                switch (data.getRealTimeLinkDeleteFlg()) {
                    case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                        // 該当あり(UPDATE)
                        // System.out.println("----- Update -----------------");
                        shainDptRepository.save(shainDptEntity);
                        returnValue.updateCount++;
                        break;
                    case RealTimeLinkDeleteFlg.DELETE:
                        // 該当あり(DELETE)
                        shainDptRepository.deleteById(shainDptEntityPk);
                        returnValue.deleteCount++;
                        break;
                    default:
                        break;
                }
            } else {
                switch (data.getRealTimeLinkDeleteFlg()) {
                    case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                        // 該当なし(INSERT)
                        shainDptRepository.save(shainDptEntity);
                        returnValue.insertCount++;
                        break;
                    case RealTimeLinkDeleteFlg.DELETE:
                        // 例外 存在しない
                        LogUtil.log("DM0001W", data.toString());
                        break;
                    default:
                        LogUtil.log("DM0002W", data.toString());
                        break;
                }
            }
        }

        return returnValue;
    }

}
