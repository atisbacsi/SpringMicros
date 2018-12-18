package org.vargattila;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

public class TrackingFilter extends ZuulFilter {
    private static final String CORRELATION_ID = "correlation_id";
    private Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public Object run() throws ZuulException {
        getCorrelationId().ifPresent(cID -> {
            logger.debug("TMX Correlation ID present: {}.", cID);
        });
        String correlationId = getCorrelationId().orElse(generateRandomUUID());
        setCorrelationId(correlationId);
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());
        return null;
    }

    private String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public Optional<String> getCorrelationId() {
        RequestContext ctx = RequestContext.getCurrentContext();

        String simaHeader = ctx.getRequest().getHeader(CORRELATION_ID);

        if (simaHeader != null) {
            return Optional.of(simaHeader);
        } else {
            return Optional.ofNullable(ctx.getZuulRequestHeaders().get(CORRELATION_ID));
        }
    }

    public void setCorrelationId(String correlationId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
        logger.debug("Correlation ID {} saved.", correlationId);
    }
}
