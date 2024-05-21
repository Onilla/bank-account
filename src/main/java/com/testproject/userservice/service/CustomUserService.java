package com.testproject.userservice.service;

import com.testproject.userservice.entity.CustomUser;
import com.testproject.userservice.exception.CustomUserException;
import com.testproject.userservice.repository.CustomUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public UUID createNewUser(@Valid CustomUser customUser, byte[] avatar) throws CustomUserException {
//        if (customUserRepository.existsByEmail(customUser.getEmail())) {
//            throw new CustomUserException("Пользователь с email: " + customUser.getEmail() + " уже существует");
//        }
        try {
            if (avatar != null && avatar.length > 0) {
                String fileName = "avatar_" + UUID.randomUUID() + ".jpg";
                String uploadDir = "avatars";

                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.write(filePath, avatar);
                customUser.setFileName(fileName);
                customUser.setAvatar(avatar);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла аватара: " + e.getMessage());

        }
        customUser.setUsername(customUser.getUsername());
        customUser.setSurname(customUser.getSurname());
        customUser.setEmail(customUser.getEmail());
        return customUserRepository.save(customUser).getId();
    }

//    private String saveFile(MultipartFile file) throws CustomUserException {
//        // TODO:: проверка на тип и размер файла
//        String fileName = "/C:\\Users\\Ольга\\Downloads/" + UUID.randomUUID();
//        try {
//            Files.write(Path.of(fileName), file.getBytes());
//            return fileName;
//        } catch (IOException e) {
//            throw new CustomUserException("Проблема загрузки файла " + e.getMessage());
//        }
//    }

    public void deleteUser(UUID clientId) throws CustomUserException {
        if (!customUserRepository.existsById(clientId)) {
            throw new CustomUserException("Пользователя с id " + clientId + " не существует");
        }
        customUserRepository.deleteById(clientId);
    }
}
