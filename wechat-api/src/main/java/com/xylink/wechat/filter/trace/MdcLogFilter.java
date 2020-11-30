package com.xylink.wechat.filter.trace;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-27
 */

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
@WebFilter(filterName = "MdcLogFilter",urlPatterns = "/*")
public class MdcLogFilter implements Filter {

    /**
     * 日志跟踪标识
     */
    private static final String TRACE_ID = "TRACE_ID";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String traceID = UUID.randomUUID().toString().replace("-","");
        MDC.put(TRACE_ID, traceID);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        MDC.clear();
    }
}
