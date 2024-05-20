package com.testproject.userservice.controller;

import com.testproject.userservice.DTO.CustomUserDTO;
import com.testproject.userservice.entity.CustomUser;
import com.testproject.userservice.exception.CustomUserException;
import com.testproject.userservice.service.CustomUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.UUID;


@RequestMapping("/user")
@RestController
@Tag(name = "CustomUserController", description = "Этот контроллер служит для работы с сущностью - пользователь ")

public class CustomUserController {

    // добавить контроллер на мультипарт файл чтобы была аватарка

    private final CustomUserService customUserService;

    public CustomUserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @GetMapping
    @Operation(summary = "Позволяет получить список всех пользователей")
    public List<CustomUser> getAllUsers() { // Iterable? нет ошибки канкарент ??
        return customUserService.getAllUsers();
    }

    @Operation(summary = "Позволяет получить пользователя по id")
    @GetMapping("/{userId}")
    public CustomUser getUserById(@PathVariable UUID userId) {
        try {
            CustomUser user = customUserService.getUserById(userId);
            return user;
        } catch (CustomUserException e) {
            throw new RuntimeException(e);
        }
    }


    @Operation(summary = "Позволяет добавить пользователя")
    @PostMapping("/add") // 201 Created header Location
    public ResponseEntity<Void> addUser(@RequestBody @Valid CustomUserDTO customUserDTO) {
        // ResponseEntity для изменения статуса ответа
        // Если у RequestBody параметр required на false,
        // передача параметра будет не обязательна, по умолчанию true, не передать будет ошибка
        try {
            UUID clientId = customUserService.saveNewUser(CustomUser.builder()
                            .username(customUserDTO.getUsername())
                            .surname(customUserDTO.getSurname())
                            .email(customUserDTO.getEmail())
                    .build());
            URI location = URI.create("https://localhost:8080/user/" + clientId);
            return ResponseEntity.created(location).build();
        } catch (CustomUserException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }
    @Operation(summary = "Позволяет удалить пользователя")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
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