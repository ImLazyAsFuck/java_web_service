package com.ss5.controller;

import com.ss5.model.dto.DataResponse;
import com.ss5.model.entity.User;
import com.ss5.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController{

    private final UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> getAll(){
        List<User> list = userService.getAllUsers();
        return ResponseEntity.ok(DataResponse.of(list, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<User>> create(@Valid @RequestBody User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DataResponse.of(saved, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<User>> getById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(DataResponse.of(user, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<User>> update(@PathVariable Long id, @Valid @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok(DataResponse.of(updated, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(DataResponse.of("Deleted successfully", HttpStatus.OK));
    }
}
