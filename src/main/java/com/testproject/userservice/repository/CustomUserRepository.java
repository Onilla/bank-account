package com.testproject.userservice.repository;

import com.testproject.userservice.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

}
