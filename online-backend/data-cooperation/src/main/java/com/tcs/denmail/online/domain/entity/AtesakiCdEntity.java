package com.tcs.denmail.online.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.tcs.denmail.common.entity.TcsBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_atesaki_cd")
@IdClass(AtesakiCdEntity.class)
public class AtesakiCdEntity extends TcsBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private String kanriNo;
    @Id
    @NotNull
    private String taishoKbn;
    @Id
    @NotNull
    private String cAtesakiCd;
    @Id
    @NotNull
    private String rAtesakiCd;


}
