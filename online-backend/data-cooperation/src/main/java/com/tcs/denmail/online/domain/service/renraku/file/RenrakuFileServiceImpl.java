package com.tcs.denmail.online.domain.service.renraku.file;

import com.tcs.denmail.common.service.TcsBaseService;
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
    public RenrakuFileEntity syncData(RenrakuFileModel in) {
        ReturnValue returnValue = new ReturnValue();

        RenrakuFileEntity.Pk pk = new RenrakuFileEntity.Pk(in.getKanriNo(), in.getRenNo());

        switch (in.getRealTimeLinkDeleteFlg()) {
            // INSERT_UPDATE
            case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                // DBよりデータを取得(キー値)

                repository.findById(pk).ifPresentOrElse(entity -> {
                    entity.setFileName(in.getFileName());
                    returnValue.entity = repository.saveAndFlush(entity);
                }, () -> {
                    RenrakuFileEntity entity = new RenrakuFileEntity();
                    entity.setKanriNo(in.getKanriNo());
                    entity.setRenNo(in.getRenNo());
                    entity.setFileName(in.getFileName());
                    returnValue.entity = repository.saveAndFlush(entity);
                });
                break;
            // DELETE
            case RealTimeLinkDeleteFlg.DELETE:
                repository.findById(pk).ifPresentOrElse(entity -> {
                    returnValue.entity = entity;
                    repository.delete(entity);
                }, () -> {
                    // TODO log
                });
                break;
            default:
                // TODO log
                break;
        }

        return returnValue.entity;
    }

    class ReturnValue {
        RenrakuFileEntity entity = new RenrakuFileEntity();
    }
}
