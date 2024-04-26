package com.testproject.userservice.controller;

import com.testproject.userservice.DTO.CustomUserDTO;
import com.testproject.userservice.entity.CustomUser;
import com.testproject.userservice.exception.CustomUserException;
import com.testproject.userservice.service.CustomUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
@Validated
@RequestMapping("/user")
@RestController
public class CustomUserController {

    private final CustomUserService customUserService;

    public CustomUserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @GetMapping
    public List<CustomUser> getAllUsers() { // Iterable?
        return customUserService.getAllUsers();
    }


    @GetMapping("/{userId}")
    public CustomUser getUserById(@PathVariable long userId) {
        try {
            CustomUser user = customUserService.getUserById(userId);
            return user;
        } catch (CustomUserException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add") // 201 Created header Location
    public ResponseEntity<Void> addUser(@RequestBody @Valid CustomUserDTO customUserDTO) {
        // ResponseEntity для изменения статуса ответа
        // Если у RequestBody параметр required на false,
        // передача параметра genre будет не обязательна, по умолчанию true, не передать будет ошибка
        try {
            long userId = customUserService.saveNewUser(CustomUser.builder()
                            .username(customUserDTO.getUsername())
                            .email(customUserDTO.getEmail())
                    .build());
            URI location = URI.create("https://localhost:8080/user/" + userId);
            return ResponseEntity.created(location).build();
        } catch (CustomUserException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        try {
            customUserService.deleteUser(userId);
            // ответ - 204, тело - пустое
            return ResponseEntity.noContent().build();
        } catch (CustomUserException e) {
            // ответ - 404, в теле передается информация об ошибке
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}