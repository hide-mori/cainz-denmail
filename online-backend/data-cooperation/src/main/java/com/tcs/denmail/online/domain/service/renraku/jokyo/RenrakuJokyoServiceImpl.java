package com.tcs.denmail.online.domain.service.renraku.jokyo;

import com.tcs.denmail.online.app.model.RenrakuJokyoModel;
import com.tcs.denmail.online.domain.condition.RealTimeLinkDeleteFlg;
import com.tcs.denmail.online.domain.entity.RenrakuJokyoEntity;
import com.tcs.denmail.online.domain.repository.RenrakuJokyoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RenrakuJokyoServiceImpl implements RenrakuJokyoService {

    @Autowired
    private RenrakuJokyoRepository repository;

    @Transactional
    @Override
    public RenrakuJokyoEntity syncData(RenrakuJokyoModel in) {
        ReturnValue returnValue = new ReturnValue();

        RenrakuJokyoEntity.Pk pk =
                new RenrakuJokyoEntity.Pk(in.getKanriNo(), in.getAtesakiTenpoCd());

        switch (in.getRealTimeLinkDeleteFlg()) {
            // INSERT_UPDATE
            case RealTimeLinkDeleteFlg.INSERT_UPDATE:
                // DBよりデータを取得(キー値)

                repository.findById(pk).ifPresentOrElse(entity -> {
                    BeanUtils.copyProperties(in, entity);
                    returnValue.entity = repository.saveAndFlush(entity);
                }, () -> {
                    RenrakuJokyoEntity entity = new RenrakuJokyoEntity();
                    BeanUtils.copyProperties(in, entity);
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
        RenrakuJokyoEntity entity = new RenrakuJokyoEntity();
    }

}
