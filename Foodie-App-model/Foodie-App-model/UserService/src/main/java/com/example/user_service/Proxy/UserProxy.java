package com.example.user_service.Proxy;

import com.example.user_service.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="UserAuthentication",url="localhost:8087")

public interface UserProxy {
    @PostMapping("/api/v1/saveUser")
    ResponseEntity<?> saveUser(@RequestBody User user);
}
