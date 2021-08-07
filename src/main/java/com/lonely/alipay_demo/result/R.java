package com.lonely.alipay_demo.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiyang
 * @FileName: R
 * @Date: Created in 2021/8/6 11:59
 * @Vserion:
 * @Description: TODO
 */
@Data
public class R {
    private Boolean status;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    private R(){}

    public static R ok(){
        R r = new R();
        r.setStatus(ResultCodeEnum.SUCCESS.getStatus());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    public static R error(){
        R r = new R();
        r.setStatus(ResultCodeEnum.UNKNOWN_REASON.getStatus());
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return r;
    }

    public static R setResult(ResultCodeEnum resultCodeEnum){
        R r = new R();
        r.setStatus(resultCodeEnum.getStatus());
        r.setCode(resultCodeEnum.getCode());
        r.setMessage(resultCodeEnum.getMessage());
        return r;
    }

    public R success(Boolean success){
        this.setStatus(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
