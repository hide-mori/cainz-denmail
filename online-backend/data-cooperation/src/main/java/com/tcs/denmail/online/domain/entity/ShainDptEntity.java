package com.tcs.denmail.online.domain.entity;

import com.tcs.denmail.common.entity.TcsBaseEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.IdClass;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "m_shain_dpt_asc")
@IdClass(ShainDptEntity.ShainDptEntityPk.class)
public class ShainDptEntity extends TcsBaseEntity {

    @Id
    private String shainCd;
    @Id
    private String dptCd;

    private String tenpoCd;

    private String systemCreateUser;
    private Date systemCreateDate;
    private String systemUpdateUser;
    private Date systemUpdateDate;
    private boolean systemLogicalDeleteFlg;
    private Integer version;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ShainDptEntityPk implements Serializable {
        private static final long serialVersionUID = 1L;
        private String shainCd;
        private String dptCd;
    }

}
