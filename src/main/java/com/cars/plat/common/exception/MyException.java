package com.cars.plat.common.exception;

import com.cars.plat.util.result.ResultEnum;

/**
 * Created by wangyupeng on 2017/12/19.
 */
public class MyException extends RuntimeException {
    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
