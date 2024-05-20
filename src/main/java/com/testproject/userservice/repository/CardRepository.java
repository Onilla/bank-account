package com.testproject.userservice.repository;

import com.testproject.userservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
