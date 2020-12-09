package com.xylink.wechat.util;

import com.xylink.wechat.exception.BusinessException;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-09
 */
public class HttpUtils {
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 判断ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request
                .getHeader("X-Requested-With")))
                || (request.getHeader("access-control-request-headers") != null && "x-requested-with".equals(request
                .getHeader("access-control-request-headers")));
    }


    /**
     * 获取cookie中的id
     *
     * @param request
     * @return
     */
    public static String getCookieToken(HttpServletRequest request) {
        String cookieToken = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (StringUtil.equals(cookie.getName(), "JSESSIONID")) {
                    cookieToken = cookie.getValue();
                    break;
                }
            }
        }
        if (StringUtil.isNotEmpty(cookieToken)) {
            return "WideField:" + cookieToken;
        } else {
            return cookieToken;
        }
    }


}
