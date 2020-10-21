package com.tcs.denmail.online.domain.service.renraku;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.tcs.denmail.common.service.TcsBaseService;
import com.tcs.denmail.online.app.model.RenrakuDetailModel;
import com.tcs.denmail.online.domain.condition.BUNSHO_KBN;
import com.tcs.denmail.online.domain.condition.DISP_FLG;
import com.tcs.denmail.online.domain.condition.HENSHIN_YOFU_FLG;
import com.tcs.denmail.online.domain.condition.RENRAKU_KBN;
import com.tcs.denmail.online.domain.condition.TANAWARI_YOFU_FLG;
import com.tcs.denmail.online.domain.constants.DmConstants;
import com.tcs.denmail.online.domain.entity.RenrakuViewEntity;
import com.tcs.denmail.online.domain.entity.RenrakuJokyoEntity;
import com.tcs.denmail.online.domain.repository.RenrakuViewRepository;
import com.tcs.denmail.online.domain.repository.RenrakuJokyoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenrakuDetailServiceImpl extends TcsBaseService implements RenrakuDetailService {

    @Autowired
    private RenrakuViewRepository renrakuViewRepository;


    @Autowired
    private RenrakuJokyoRepository renrakuJokyoRepository;

    @Override
    public RenrakuDetailModel getRenrakuDetail(String kanriNo, String atesakiTenpoCd) {

        Optional<RenrakuViewEntity> oRenrakuEntity = renrakuViewRepository.findById(kanriNo);


        Optional<RenrakuJokyoEntity> oRenrakuJokyoEntity =
                renrakuJokyoRepository.findById(new RenrakuJokyoEntity.Pk(kanriNo, atesakiTenpoCd));

        return mapToRenrakuDetailModel(oRenrakuEntity.orElseGet(RenrakuViewEntity::new),
                oRenrakuJokyoEntity.orElseGet(RenrakuJokyoEntity::new));

    }

    private RenrakuDetailModel mapToRenrakuDetailModel(RenrakuViewEntity renrakuEntity,
            RenrakuJokyoEntity renrakuJokyoEntity) {

        RenrakuDetailModel model = new RenrakuDetailModel();
        BeanUtils.copyProperties(renrakuEntity, model);

        // タイトル
        if (RENRAKU_KBN.TENCHO_MAIL.getCode().equals(renrakuEntity.getRenrakuKbn())) {
            model.setTitle(DmConstants.TITLE_TENCHO_MAIL);
        } else {
            String title = BUNSHO_KBN.RENRAKU.getCode().equals(renrakuEntity.getBunshoKbn())
                    ? DmConstants.TITLE_RENRAKU
                    : DmConstants.TITLE_SAGYO;
            model.setTitle(title);
        }

        // 主題 [DISP_FLG]が'1'の場合のみ表示
        if (renrakuEntity.getShudai() != null
                && DISP_FLG.TRUE.getCode().equals((renrakuEntity.getShudai().getDispFlg()))) {
            model.setShudaiNm(renrakuEntity.getShudai().getShudaiNm());
        }

        // 店舗
        model.setTenpoAll(renrakuJokyoEntity.getAtesakiTenpoCd() + DmConstants.SPACE
                + renrakuJokyoEntity.getAtesakiTenpoRyakuNm());

        // 返信要不要
        model.setHenshinYofuText(
                HENSHIN_YOFU_FLG.getValueByCode(renrakuEntity.getHenshinYofuFlg()));
        if (HENSHIN_YOFU_FLG.FALSE.getCode().equals(renrakuEntity.getHenshinYofuFlg())) {
            model.setHenshinKigenYmd(HENSHIN_YOFU_FLG.FALSE.getPlaceholder());
        } else {
            String henshinHohoText = renrakuEntity.getHenshinHoho() == null ? null
                    : renrakuEntity.getHenshinHoho().getNmKj();
            model.setHenshinHohoText(henshinHohoText);
        }

        // 棚割報告要不要
        model.setTanawariYofuText(
                TANAWARI_YOFU_FLG.getValueByCode(renrakuEntity.getTanawariYofuFlg()));

        // 添付ファイルリスト
        List<String> files = renrakuEntity.getAttachmentFiles().stream().map(e -> e.getFileName())
                .collect(Collectors.toList());
        model.setAttachmentFiles(files);

        return model;
    }
}
