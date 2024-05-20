package com.testproject.userservice.service.implementation;

import com.testproject.userservice.entity.PaymentSystem;
import com.testproject.userservice.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.testproject.userservice.DTO.RequestCardDto;

import com.testproject.userservice.entity.Card;

import com.testproject.userservice.repository.CardRepository;
import com.testproject.userservice.service.CardOrderService;
import com.testproject.userservice.util.Util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardOrderServiceImplementation implements CardOrderService {
    private final CardRepository cardRepository;
    private final Util util;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${NEW_CARD_TOPIC}")
    private String newCardTopic;

    @Override
    public RequestCardDto handleRequestForNewCard(UUID clientId) {
        RequestCardDto requestCardDto = new RequestCardDto(clientId);
        kafkaTemplate.send(newCardTopic, requestCardDto);
        return requestCardDto;
    }

    @Override
    public void createCard(UUID clientId) {
        Card card = getNewCard(clientId);
        cardRepository.save(card);
    }

    private Card getNewCard(UUID clientId) {
        return Card.builder()
                .cardNumber(util.generateCardNumber())
                .paymentSystem(PaymentSystem.MIR)
                //   .status(CardStatusName.ACTIVE)
                .amount(BigDecimal.valueOf(10000))
                .expirationDate(LocalDate.now().plusYears(5))
                .holderName("CARD HOLDER")
                //  .digitalWallet(utils.randomWallet())
                // .isDefault(!isExistClientCard(clientId))
                //.pinCode(passwordEncoder.encode(RandomStringUtils.random(4, false, true)))
                .build();
    }
}
//    private boolean isExistClientCard(UUID clientId) {
//        List<Card> listCards = cardRepository.findByAccountClientId(clientId);
//        return !listCards.isEmpty();
//    }

//    private Account getNewAccount(UUID clientId) {
//        return Account
//                .builder()
//                .clientId(clientId)
//                .currencyCode(CurrencyCode.RUB)
//                .currentBalance(BigDecimal.valueOf(1000.00))
//                .openDate(LocalDate.now())
//                .isActive(true)
//                .blockedSum(BigDecimal.valueOf(10))
//                .build();
//    }


