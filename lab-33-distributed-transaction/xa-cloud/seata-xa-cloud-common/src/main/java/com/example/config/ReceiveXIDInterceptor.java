package com.example.config;

import io.seata.core.context.RootContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ReceiveXIDInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String xid = request.getHeader(RootContext.KEY_XID);
        if (StringUtils.isNotBlank(xid)) {
            RootContext.bind(xid);
        }
        return true;
    }
}
