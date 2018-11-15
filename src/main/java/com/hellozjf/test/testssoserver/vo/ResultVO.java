package com.hellozjf.test.testssoserver.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by 廖师兄
 * 2017-01-21 13:34
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码.
     */
    private String code;

    /**
     * 提示信息.
     */
    private String message;

    /**
     * 具体的内容.
     */
    private T data;
}
