package com.ms.userms.controllers;

import com.ms.userms.dtos.UserDTO;
import com.ms.userms.entities.UserEntity;
import com.ms.userms.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserEntity> saveUser(@RequestBody @Valid UserDTO userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
    }

}
