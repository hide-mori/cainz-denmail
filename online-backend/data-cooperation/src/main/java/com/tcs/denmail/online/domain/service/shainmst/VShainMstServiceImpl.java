package com.tcs.denmail.online.domain.service.shainmst;

import java.util.List;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.common.util.LogUtil;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.VShainMstModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.VShainMstEntity;
import com.tcs.denmail.online.domain.repository.VShainMstRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VShainMstServiceImpl extends TcsBaseService implements VShainMstService {

    @Autowired
    private VShainMstRepository repository;

    @Transactional
    @Override
    public CooperationResponseModel syncData(List<VShainMstModel> dataList) {
        CooperationResponseModel returnValue = new CooperationResponseModel();

        for (VShainMstModel data : dataList) {

            switch (data.getRealTimeLinkDeleteFlg()) {
                // INSERT_UPDATE
                case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                    // DBよりデータを取得(キー値)
                    repository.findById(data.getShainCd()).ifPresentOrElse(entity -> {
                        // UPDATE
                        BeanUtils.copyProperties(data, entity);
                        repository.save(entity);
                        returnValue.updateCount++;
                    }, () -> {
                        // INSERT
                        VShainMstEntity entity = new VShainMstEntity();
                        BeanUtils.copyProperties(data, entity);
                        repository.save(entity);
                        returnValue.insertCount++;
                    });
                    break;
                // DELETE
                case RealTimeLinkDeleteFlg.DELETE:
                    repository.findById(data.getShainCd()).ifPresentOrElse(entity -> {
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
