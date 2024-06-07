package com.example.config;


import io.seata.core.context.RootContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class XIDIntercepter implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        if (StringUtils.isNotBlank(RootContext.getXID())) {
            httpRequest.getHeaders().add(RootContext.KEY_XID, RootContext.getXID());
        }

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
