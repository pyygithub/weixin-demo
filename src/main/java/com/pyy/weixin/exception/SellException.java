package com.pyy.weixin.exception;


import com.pyy.weixin.enums.ResultEnum;

/**
 * 2018-06-11 18:55
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
