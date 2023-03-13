package com.project.UserAuthApp.proxy;

import com.project.UserAuthApp.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="UserServiceApp",url = "localhost:999")
public interface UserProxy {
    @PostMapping("/userService/addUser")
    public ResponseEntity<?> sendDataToUserServiceApp(@RequestBody UserDTO userDTO);
}
