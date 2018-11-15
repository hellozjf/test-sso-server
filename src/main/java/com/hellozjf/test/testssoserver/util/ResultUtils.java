package com.hellozjf.test.testssoserver.util;


import com.hellozjf.test.testssoserver.vo.ResultVO;

/**
 * Created by 廖师兄
 * 2017-01-21 13:39
 */
public class ResultUtils {

    public static ResultVO success(Object object) {
        ResultVO result = new ResultVO();
        result.setCode("SUCCESS");
        result.setMessage("");
        result.setData(object);
        return result;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(String code, String msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}
