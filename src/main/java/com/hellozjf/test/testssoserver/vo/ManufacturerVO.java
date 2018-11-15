package com.hellozjf.test.testssoserver.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @author Jingfeng Zhou
 */
@Data
public class ManufacturerVO {

    @JsonProperty("client_id")
    private String clientId;

    private String name;

    @JsonProperty("client_secret")
    private String clientSecret;
}
