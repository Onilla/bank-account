package com.testproject.userservice.service;

import com.testproject.userservice.entity.CustomUser;
import com.testproject.userservice.exception.CustomUserException;
import com.testproject.userservice.repository.CustomUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
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

    public CustomUser getUserById(Long id) throws CustomUserException {
        return customUserRepository.findById(id)
                .orElseThrow(() -> new CustomUserException("Пользователь с id = " +
                        id + "не найден"));
    }

    public long saveNewUser(@Valid CustomUser customUser) throws CustomUserException {
        if (customUserRepository.existsById(customUser.getId())) {
            throw new CustomUserException("Пользователь с id: " + customUser.getId() + " уже существует");
        }
        return customUserRepository.save(customUser).getId();
    }

    public void deleteUser(Long id) throws CustomUserException {
            if (!customUserRepository.existsById(id)) {
                throw new CustomUserException("Пользователя с id " + id + " не существует");
            }
            customUserRepository.deleteById(id);
        }
    }
