package com.tcs.denmail.online.domain.service.renraku.file;

import java.util.List;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.CooperationResponseModel;
import com.tcs.denmail.online.app.model.RenrakuFileModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.RenrakuFileEntity;
import com.tcs.denmail.online.domain.repository.RenrakuFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RenrakuFileServiceImpl extends TcsBaseService implements RenrakuFileService {

    @Autowired
    private RenrakuFileRepository repository;

    @Transactional
    @Override
    public CooperationResponseModel syncData(List<RenrakuFileModel> dataList) {
        CooperationResponseModel returnValue = new CooperationResponseModel();

        for (RenrakuFileModel data : dataList) {
            RenrakuFileEntity.Pk pk = new RenrakuFileEntity.Pk(data.getKanriNo(), data.getRenNo());

            switch (data.getRealTimeLinkDeleteFlg()) {
                // INSERT_UPDATE
                case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                    // DBよりデータを取得(キー値)

                    repository.findById(pk).ifPresentOrElse(entity -> {
                        entity.setFileName(data.getFileName());
                        repository.save(entity);
                        returnValue.updateCount++;
                    }, () -> {
                        RenrakuFileEntity entity = new RenrakuFileEntity();
                        entity.setKanriNo(data.getKanriNo());
                        entity.setRenNo(data.getRenNo());
                        entity.setFileName(data.getFileName());
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
