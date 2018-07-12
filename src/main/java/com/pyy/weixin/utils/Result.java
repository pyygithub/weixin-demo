package com.pyy.weixin.utils;

import com.pyy.weixin.enums.ResultEnum;
import lombok.Data;

/**
 * Created by Administrator on 2018/7/12 0012.
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

    public static Result ok(){
        return new Result(ResultEnum.SUCCESS);
    }

    public static Result fail(){
        return new Result(ResultEnum.FAIL);
    }
}
