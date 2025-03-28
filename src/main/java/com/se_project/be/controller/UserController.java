package com.se_project.be.controller;

import com.se_project.be.dto.request.UserInputRequestDTO;
import com.se_project.be.dto.request.UserLoginRequestDTO;
import com.se_project.be.dto.request.UserRequestDTO;
import com.se_project.be.dto.response.CommonApiResponse;
import com.se_project.be.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserResource userResource;

    @PostMapping("/login")
    public ResponseEntity<CommonApiResponse> login(@RequestBody UserLoginRequestDTO request) {
        return userResource.login(request);
    }
    @PostMapping("/register")
    public ResponseEntity<CommonApiResponse> register(@RequestBody UserRequestDTO request) {
        return userResource.register(request);
    }
}
