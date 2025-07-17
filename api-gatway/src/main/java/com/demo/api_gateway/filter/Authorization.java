package com.demo.api_gateway.filter;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Authorization {


    private final Map<String, List<String>> endpointRoleMap = Map.of(
            "/admin", List.of("ADMIN"),
            "/user", List.of("USER", "ADMIN")
    );

    public boolean isAuthorized(String path, List<String> userRoles) {
        for (Map.Entry<String, List<String>> entry : endpointRoleMap.entrySet()) {
            if (path.startsWith(entry.getKey())) {
                for (String role : userRoles) {
                    if (entry.getValue().contains(role)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true; // Allow if no mapping exists
    }
}
