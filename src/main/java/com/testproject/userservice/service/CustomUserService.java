package com.testproject.userservice.service;

import com.testproject.userservice.entity.CustomUser;
import com.testproject.userservice.exception.CustomUserException;
import com.testproject.userservice.repository.CustomUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@Service
public class CustomUserService {
    private CustomUserRepository customUserRepository;

    @Autowired
    public CustomUserService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    public List<CustomUser> getAllUsers() {
        return customUserRepository.findAll();
    }

    public CustomUser getUserById(UUID id) throws CustomUserException {
        return customUserRepository.findById(id)
                .orElseThrow(() -> new CustomUserException("Пользователь с id = " +
                        id + "не найден"));
    }

    public UUID saveNewUser(@Valid CustomUser customUser) throws CustomUserException {
//        if (customUserRepository.existsByEmail(customUser.getEmail())) {
//            throw new CustomUserException("Пользователь с email: " + customUser.getEmail() + " уже существует");
//        }
        return customUserRepository.save(customUser).getId();
    }

    public void deleteUser(UUID clientId) throws CustomUserException {
            if (!customUserRepository.existsById(clientId)) {
                throw new CustomUserException("Пользователя с id " + clientId + " не существует");
            }
            customUserRepository.deleteById(clientId);
        }
    }
