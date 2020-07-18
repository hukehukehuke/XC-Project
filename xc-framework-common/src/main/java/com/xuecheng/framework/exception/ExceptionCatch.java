package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统一异常捕获类
 */
@ControllerAdvice  //控制器增强
public class ExceptionCatch {

    private static  final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    private  static ImmutableMap<Class<? extends  Throwable>,ResultCode> EXCEPTIONS;
    private  static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    @ExceptionHandler(CustomerException.class)
    @ResponseBody
    public ResponseResult customerExcpetion(CustomerException customerException){
        ResultCode resultCode = customerException.getResultCode();
        LOGGER.error(customerException.getMessage());
        return new ResponseResult(resultCode);
    }

    @ExceptionHandler(CustomerException.class)
    @ResponseBody
    public ResponseResult excpetion(Exception excpetion){

        LOGGER.error(excpetion.getMessage());
        if(EXCEPTIONS == null){
            EXCEPTIONS = builder.build();
        }
        ResultCode resultCode = EXCEPTIONS.get(excpetion.getClass());
        if(resultCode == null){
            return new ResponseResult(resultCode);
        }else{
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }
    }

    static {
        builder.put(HttpMediaTypeException.class,CommonCode.ISVALITE);
    }
}
