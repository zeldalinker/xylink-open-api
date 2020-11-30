package com.xylink.wechat.filter;

import com.ainemo.libra.shared.enums.ErrorStatus;
import com.google.common.collect.Maps;
import com.xylink.wechat.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 林骏
 * version: v1
 * date: 2020-10-23
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final static Logger logger  = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    /**
     * 业务异常
     * @param exception 业务异常
     * @return response
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Map<String, Object> handler(BusinessException exception) {
        logger.info(" http 请求异常 ", exception);
        Map<String, Object> result = Maps.newHashMap();
        result.put("errorCode", ErrorStatus.LOGIN_INVALID_ACCOUNT_OR_PASSWORD.getErrorCode());
        result.put("userMessage", ErrorStatus.LOGIN_INVALID_ACCOUNT_OR_PASSWORD.getResId());
        result.put("info",exception.getCause().getMessage());
        return result;
    }


    /**
     * http请求异常
     * @param exception http 异常
     * @return response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Map<String, Object> handler(HttpMessageNotReadableException exception) {
        logger.info(" http 请求异常 ", exception);
        Map<String, Object> result = Maps.newHashMap();
        result.put("errorCode", ErrorStatus.LOGIN_INVALID_ACCOUNT_OR_PASSWORD.getErrorCode());
        result.put("userMessage", ErrorStatus.LOGIN_INVALID_ACCOUNT_OR_PASSWORD.getResId());
        return result;
    }




}
