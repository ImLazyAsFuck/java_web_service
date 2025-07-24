package com.b910.controller;

import com.b910.model.dto.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @PutMapping("profile/edit")
    public ResponseEntity<APIResponse<?>> editProfile(){
        return ResponseEntity.ok(new APIResponse<>(true, "Edit profile successfully!", null, HttpStatus.OK));
    }
}
