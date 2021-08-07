package com.lonely.alipay_demo.result;

import lombok.Data;

/**
 * @Author: xiyang
 * @FileName: BusinessException
 * @Date: Created in 2021/8/6 12:15
 * @Vserion:
 * @Description: TODO
 */
@Data
public class BusinessException extends RuntimeException {

    private Boolean status;

    private Integer code;

    private String message;

    public BusinessException(Integer code, String message) {
        this.status = false;
        this.code = code;
        this.message = message;
    }

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        this.status = false;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
