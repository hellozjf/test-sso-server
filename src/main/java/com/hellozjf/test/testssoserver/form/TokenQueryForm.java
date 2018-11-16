package com.hellozjf.test.testssoserver.form;

import lombok.Data;

/**
 * @author Jingfeng Zhou
 */
@Data
public class TokenQueryForm {
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String code;
    private String redirectUrl;
}
