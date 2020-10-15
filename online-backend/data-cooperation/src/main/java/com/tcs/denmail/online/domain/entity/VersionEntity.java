package com.tcs.denmail.online.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Data;

@Data
@Entity
@Table(name = "m_version")
public class VersionEntity {

    @Id
    private Integer id;

    private String name;

    @Version
    private Integer version;


}
