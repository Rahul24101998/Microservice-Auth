package com.demo.api_gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator{

    public RouteValidator() {
        System.out.println("✅ RouteValidator constructor invoked");
    }


    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/auth/validateToken",
            "/auth/refreshToken"
    );

    public Predicate<ServerHttpRequest> isSecured = request -> {
        String path = request.getURI().getPath();
        System.out.println("🔍 Checking secured route for: " + path);
        return openApiEndpoints.stream()
                .noneMatch(path::equalsIgnoreCase); // or path.startsWith(uri)
    };
}
