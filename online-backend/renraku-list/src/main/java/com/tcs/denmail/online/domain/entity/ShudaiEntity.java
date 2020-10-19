package com.tcs.denmail.online.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 主題Entity
 */
@Data
@Entity
@Table(name = "m_shudai")
public class ShudaiEntity {

    @Id
    private String shudaiCd;

    private String shudaiNm;

    private String dispFlg;

}