package com.tcs.denmail.online.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AtesakiCdModel {

    private String kanriNo;

    private String taishoKbn;

    @JsonProperty("cAtesakiCd")
    private String cAtesakiCd;

    @JsonProperty("rAtesakiCd")
    private String rAtesakiCd;

    private String realTimeLinkDeleteFlg;

}
