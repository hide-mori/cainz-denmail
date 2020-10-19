package com.tcs.denmail.online.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "M_SHAIN_DPT_ASC")
@IdClass(TMShainDptAscEntity.TMShainDptAscEntityPk.class)
public class TMShainDptAscEntity {

    @Id
    private String shainCd;
    @Id
    private String dptCd;
    @Id
    private String tenpoCd;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TMShainDptAscEntityPk implements Serializable {
        private static final long serialVersionUID = 1L;
        private String shainCd;
        private String dptCd;
        private String tenpoCd;
    }
}
