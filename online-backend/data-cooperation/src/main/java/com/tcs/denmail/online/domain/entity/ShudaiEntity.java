package com.tcs.denmail.online.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.tcs.denmail.common.entity.TcsBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 主題Entity
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "m_shudai")
public class ShudaiEntity extends TcsBaseEntity {

    @Id
    @NotNull
    private String shudaiCd;

    @NotNull
    private String shudaiNm;

    @NotNull
    private String dispFlg;

}
