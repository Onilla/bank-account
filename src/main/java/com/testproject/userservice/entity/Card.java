package com.testproject.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    @Column(name = "card_number", unique = true)
    private String cardNumber;
    //   private String accountNumber;
    @Column(name = "payment_system", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PaymentSystem paymentSystem;

    private BigDecimal amount;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name = "holder_name")
    private String holderName;


}
