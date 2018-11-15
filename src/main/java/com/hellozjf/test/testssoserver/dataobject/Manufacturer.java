package com.hellozjf.test.testssoserver.dataobject;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author Jingfeng Zhou
 */
@Data
@Entity
public class Manufacturer extends BaseEntity {

    private String secret;
    private String code;
    private String redirectUri;
    private String token;
    private Long tokenExpireMsTime;

    private String zrrdah;
    private String sfzjlxDm;
    private String sfzjhm;
    private String xm;
    private String gjhdqszDm;
}
