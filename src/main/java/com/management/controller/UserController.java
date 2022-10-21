package com.management.controller;

import com.management.dto.UserDto;
import com.management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok().body(userService.createUser(dto));
    }

    @GetMapping("/update")
    public ResponseEntity<?> updateSalary(@RequestParam Integer userId, @RequestParam Integer salary) {
        return ResponseEntity.ok().body(userService.updateSalary(userId, salary));
    }
}
