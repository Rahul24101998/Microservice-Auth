package com.demo.api_gateway.filter;

import com.demo.api_gateway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate template;

    @Autowired
    Authorization authorization;

    @Autowired
    JwtUtils jwtUtils;

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
                   List<String> userRoles = jwtUtils.getRoles(authHeader);
                   String path = exchange.getRequest().getURI().getPath();
                   if (!authorization.isAuthorized(path, userRoles)) {
                       throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to access this resource");
                   }

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
