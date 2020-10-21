package com.tcs.denmail.online.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import com.tcs.denmail.common.entity.TcsBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "m_nm")
@IdClass(NameMasterEntity.Pk.class)
public class NameMasterEntity extends TcsBaseEntity {

    @Id
    private String nmKbn;
    @Id
    private String nmCd;
    private String nmKj;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {
        private static final long serialVersionUID = 1L;
        private String nmKbn;
        private String nmCd;
    }
}
