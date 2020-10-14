package com.tcs.denmail.common.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TcsBaseEntity {

    @Column(name = "system_create_user")
    private String systemCreateUser;

    @Column(name = "system_create_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreateDate;

    @Column(name = "system_update_user")
    private String systemUpdateUser;

    @Column(name = "system_update_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemUpdateDate;

    @Column(name = "system_logical_delete_flg")
    private boolean systemLogicalDeleteFlg;

    @Column(name = "version")
    private Integer version;

}
