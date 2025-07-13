package com.demo.api_gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Config> {
    public LogFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("📡 LogFilter triggered for: " + exchange.getRequest().getURI().getPath());
            return chain.filter(exchange);
        };
    }

    public static class Config {}
}