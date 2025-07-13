package com.demo.api_gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate template;

    public AuthenticationFilter() {
        super(Config.class);
        System.out.println("✅ AuthenticationFilter constructor invoked");

    }

   @Override
    public GatewayFilter apply(Config config) {

       return ((exchange, chain) -> {
           if (routeValidator == null) {
               throw new RuntimeException("routeValidator is NULL — check Spring injection");
           }
           System.out.println("Path: " + exchange.getRequest().getURI().getPath());
           if (routeValidator.isSecured.test(exchange.getRequest())) {
               //header contains token or not
               if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                   System.out.println("Missing Authorization Header");
                   throw new RuntimeException("missing authorization header");
               }

               String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
               if (authHeader != null && authHeader.startsWith("Bearer ")) {
                   authHeader = authHeader.substring(7);
               }
               try {
                   template.getForObject("http://authentication-server/auth/validateToken?token=" + authHeader, String.class);

               } catch (Exception e) {
                     throw new RuntimeException("Invalid token: " + e.getMessage());

               }
           }
           System.out.println("🛡️ Path: " + exchange.getRequest().getURI().getPath());
           System.out.println("🛡️ Is Secured: " + routeValidator.isSecured.test(exchange.getRequest()));

           return chain.filter(exchange);
       });
   }

    public static class Config {
        // Add configuration properties if needed
    }
}
