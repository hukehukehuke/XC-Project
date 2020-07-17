package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 自定义异常类型
 */
public class CustomerException extends RuntimeException {
    ResultCode resultCode;

    public CustomerException(ResultCode resultCode){
        this.resultCode = resultCode;
    }
    public  ResultCode getResultCode(){
        return resultCode;
    }
}
