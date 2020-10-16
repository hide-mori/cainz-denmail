package com.tcs.denmail.online.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.tcs.denmail.common.entity.TcsBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_v_shain_mst")
public class VShainMstEntity extends TcsBaseEntity {

    @NotNull
    private String tenCd;
    private String tenRyakuMeiKj;
    @Id
    @NotNull
    private String shainCd;
    @NotNull
    private String shainNm;
    private String jigyoHonbuCd;
    private String areaCd;
    private String honBushoCd;
    private Integer shokuiDankaiLevel;
    private String shokuiCd;
    private String shokuiNm;
    @NotNull
    private String dmlShokuiCd;
    @NotNull
    private String dmlShokuiNm;
    private String bushoCd;
    private String bushoNm;
    @NotNull
    private String spFlg;

}
