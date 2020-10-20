package com.tcs.denmail.online.domain.service.renraku.jokyo;

import java.util.List;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.RenrakuJokyoModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.RenrakuJokyoEntity;
import com.tcs.denmail.online.domain.repository.RenrakuJokyoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RenrakuJokyoServiceImpl extends TcsBaseService implements RenrakuJokyoService {

    @Autowired
    private RenrakuJokyoRepository repository;

    @Transactional
    @Override
    public CooperationResponseModel syncData(List<RenrakuJokyoModel> dataList) {
        CooperationResponseModel returnValue = new CooperationResponseModel();

        for (RenrakuJokyoModel data : dataList) {

            RenrakuJokyoEntity.Pk pk =
                    new RenrakuJokyoEntity.Pk(data.getKanriNo(), data.getAtesakiTenpoCd());

            switch (data.getRealTimeLinkDeleteFlg()) {
                // INSERT_UPDATE
                case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                    // DBよりデータを取得(キー値)
                    repository.findById(pk).ifPresentOrElse(entity -> {
                        BeanUtils.copyProperties(data, entity);
                        repository.save(entity);
                        returnValue.updateCount++;
                    }, () -> {
                        RenrakuJokyoEntity entity = new RenrakuJokyoEntity();
                        BeanUtils.copyProperties(data, entity);
                        repository.save(entity);
                        returnValue.insertCount++;
                    });
                    break;
                // DELETE
                case RealTimeLinkDeleteFlg.DELETE:
                    repository.findById(pk).ifPresentOrElse(entity -> {
                        repository.delete(entity);
                        returnValue.deleteCount++;
                    }, () -> {
                        // TODO log
                    });
                    break;
                default:
                    // TODO log
                    break;
            }
        }
        return returnValue;
    }

}
