package com.tcs.denmail.online.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.tcs.denmail.common.entity.TcsBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_renraku_file")
@IdClass(RenrakuFileEntity.Pk.class)
public class RenrakuFileEntity extends TcsBaseEntity {

    @Id
    @NotNull
    private String kanriNo;
    @Id
    @NotNull
    private Integer renNo;
    @NotNull
    private String fileName;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {

        private static final long serialVersionUID = 1L;

        private String kanriNo;

        private Integer renNo;
    }

}
