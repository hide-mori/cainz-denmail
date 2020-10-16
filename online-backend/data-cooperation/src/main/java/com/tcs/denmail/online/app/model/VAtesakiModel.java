package com.tcs.denmail.online.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VAtesakiModel {

    @JsonProperty("crAtesakiCd")
    private String crAtesakiCd;
    @JsonProperty("crAtesakiNm")
    private String crAtesakiNm;
    @JsonProperty("cAtesakiCd")
    private String cAtesakiCd;
    @JsonProperty("rAtesakiCd")
    private String rAtesakiCd;
    @JsonProperty("cSort")
    private Integer cSort;
    @JsonProperty("rSort")
    private Integer rSort;
    private String realTimeLinkDeleteFlg;
}
