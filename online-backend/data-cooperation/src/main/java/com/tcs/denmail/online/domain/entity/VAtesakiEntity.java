package com.tcs.denmail.online.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.tcs.denmail.common.entity.TcsBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 宛先マスタビュー
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_v_atesaki")
public class VAtesakiEntity extends TcsBaseEntity {

    @Id
    @NotNull
    private String crAtesakiCd;
    @NotNull
    private String crAtesakiNm;
    @NotNull
    private String cAtesakiCd;
    @NotNull
    private String rAtesakiCd;
    @NotNull
    private Integer cSort;
    @NotNull
    private Integer rSort;


}
