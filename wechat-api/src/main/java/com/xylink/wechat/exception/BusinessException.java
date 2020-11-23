package com.xylink.wechat.exception;

/**
 * @author 林骏
 * version: v1
 * date: 2020-10-23
 */
public class BusinessException extends Throwable{

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message,Throwable throwable) {
        super(message,throwable);
    }

    @Override
    public String getMessage(){
       return super.getMessage();
    }
}
