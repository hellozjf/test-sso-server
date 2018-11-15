package com.hellozjf.test.testssoserver.dataobject;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author Jingfeng Zhou
 */
@Data
@Entity
public class Test extends BaseEntity {
    private String name;
    private Integer age;
    private Double score;
}
