package com.ss6.controller;

import com.ss6.model.dto.DataResponse;
import com.ss6.model.entity.User;
import com.ss6.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController{

    private final UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> getAllUsers(){
        return ResponseEntity.ok(new DataResponse<>(userService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<User>> saveUser(@RequestBody User user){
        return ResponseEntity.ok(new DataResponse<>(userService.save(user), HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<DataResponse<User>> updateUser(@RequestBody User user){
        return ResponseEntity.ok(new DataResponse<>(userService.update(user), HttpStatus.OK));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<User>> getUserById(Long id){
        return ResponseEntity.ok(new DataResponse<>(userService.findById(id), HttpStatus.OK));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataResponse<Void>> deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.NO_CONTENT));
    }
}
