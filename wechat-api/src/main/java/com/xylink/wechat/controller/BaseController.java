package com.xylink.wechat.controller;


import com.xylink.wechat.exception.BusinessException;
import com.xylink.wechat.util.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-09
 */
public abstract class BaseController {
   void validClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        //微信客户端
//        if(!userAgent.contains("micromessenger")){
//            response.sendRedirect("/");
//        }
    }
}
