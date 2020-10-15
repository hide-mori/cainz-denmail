package com.tcs.denmail.online.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.tcs.denmail.common.exception.TcsApplicationException;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.common.util.DateUtil;
import com.tcs.denmail.online.app.model.ShainDptModel;
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
    public List<ShainDptEntity> getShainDpt(List<ShainDptModel> shainDptList)
            throws TcsApplicationException {

        // TEST用（結果確認用）
        // System.out.println("***** Service(Start) *****");
        List<ShainDptEntity> shainDptEntities = new ArrayList<ShainDptEntity>();

        for (int i = 0; i < shainDptList.size(); ++i) {

            // TEST用：受信データ(JSON形式)の内容
            // System.out.println("------------------------------");
            // System.out.println("shainCd(" + i + ")=> " +
            // shainDptList.get(i).getShainCd());
            // System.out.println("dptCd(" + i + ")=> " + shainDptList.get(i).getDptCd());
            // System.out.println("tenpoCd(" + i + ")=> " +
            // shainDptList.get(i).getTenpoCd());
            // System.out.println("realTimeLinkDeleteFlg(" + i + ")=> " +
            // shainDptList.get(i).getRealTimeLinkDeleteFlg());

            // 受信データ⇒Entity
            ShainDptEntity shainDptEntity = new ShainDptEntity();
            shainDptEntity.setShainCd(shainDptList.get(i).getShainCd());
            shainDptEntity.setDptCd(shainDptList.get(i).getDptCd());
            shainDptEntity.setTenpoCd(shainDptList.get(i).getTenpoCd());
            // (仮)
            shainDptEntity.setSystemCreateUser("99999999");
            shainDptEntity.setSystemCreateDate(DateUtil.getDatetime());
            shainDptEntity.setSystemUpdateUser("99999999");
            shainDptEntity.setSystemUpdateDate(DateUtil.getDatetime());
            shainDptEntity.setSystemLogicalDeleteFlg(false);
            shainDptEntity.setVersion(0);
            // System.out.println(shainDptEntity.toString());
            ShainDptEntityPk shainDptEntityPk = new ShainDptEntityPk(
                    shainDptList.get(i).getShainCd(), shainDptList.get(i).getDptCd());

            // DBよりデータを取得(キー値)
            Optional<ShainDptEntity> shainDptOpt = shainDptRepository.findById(shainDptEntityPk);
            if (shainDptOpt.isPresent()) {
                switch (shainDptList.get(i).getRealTimeLinkDeleteFlg()) {
                    case "0":
                        // 該当あり(UPDATE)
                        // System.out.println("----- Update -----------------");
                        shainDptRepository.save(shainDptEntity);

                        // TEST用(結果確認用)
                        shainDptEntities.add(shainDptEntity);
                        break;
                    case "1":
                        // 該当あり(DELETE)
                        // System.out.println("----- Delete -----------------");
                        shainDptRepository.deleteById(shainDptEntityPk);
                        break;
                }
            } else {
                switch (shainDptList.get(i).getRealTimeLinkDeleteFlg()) {
                    case "0":
                        // 該当なし(INSERT)
                        // System.out.println("----- Insert -----------------");
                        shainDptRepository.save(shainDptEntity);

                        // TEST用(結果確認用)
                        shainDptEntities.add(shainDptEntity);
                        break;
                    default:
                        // TEST用(例外)
                        System.out.println("-----[not exist]--------------");
                }
            }
        }

        // TEST用(結果確認用)
        // System.out.println("***** Service(End) *****");
        return shainDptEntities;
    }

}
