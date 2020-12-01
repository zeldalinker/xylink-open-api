package com.xylink.admin.exception;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-01
 */
public class CommonException extends RuntimeException {
    public Throwable e;

    public CommonException(Throwable e){
        super(e);
    }
}
