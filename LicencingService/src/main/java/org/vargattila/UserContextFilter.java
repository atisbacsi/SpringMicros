package org.vargattila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        String correlationId = servletRequest.getHeader(UserContext.CORRELATION_ID);
        UserContextHolder.getContext().setCorrelationId(correlationId);

        logger.info("headerben a correlationId:" + correlationId);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
