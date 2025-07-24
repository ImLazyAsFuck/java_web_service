package com.b910.controller;

import com.b910.model.dto.request.UserLogin;
import com.b910.model.dto.request.UserRegister;
import com.b910.model.dto.response.APIResponse;
import com.b910.model.dto.response.JWTResponse;
import com.b910.model.entity.User;
import com.b910.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<APIResponse<User>> registerUser(@RequestBody UserRegister userRegister){
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(true,"Regiser user successfully!",userService.registerUser(userRegister), HttpStatus.CREATED));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<JWTResponse>> login(@RequestBody UserLogin userLogin){
        return ResponseEntity.ok(new APIResponse<>(true,"Login successfully!",userService.login(userLogin), HttpStatus.OK));
    }
}
