package com.testproject.userservice.configuration;

import com.testproject.userservice.service.CardOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.testproject.userservice.DTO.RequestCardDto;


@Component
@RequiredArgsConstructor
public class KafkaListenerMessage {
    private final CardOrderService cardOrderService;

    @KafkaListener(id = "${LISTENER_ID_NEW_CARD}", topics = {"${NEW_CARD_TOPIC}"}, groupId = "${spring.kafka.consumer.group-id}")
    public void handleMessage(RequestCardDto requestCarDto) {
        cardOrderService.createCard(
                requestCarDto.getClientId());
        //     requestCarDto.getCardProductId());
    }

//    @KafkaListener(id = "${DEPOSIT_LISTENER_ID}", topics = {"${NEW_DEPOSIT_TOPIC}"}, groupId = "${spring.kafka.consumer.group-id}")
//    public void handleMessageDeposit(RequestDepositDto requestDepositDto) throws MinDurationMonthException {
//        depositOrderService.createDeposit(
//                requestDepositDto.getDepositOrderParams());
//    }
//
//    @KafkaListener(id = "${LISTENER_ID_CHANGE_CARD_STATUS}", topics = {"${CHANGE_CARD_STATUS_TOPIC}"}, groupId = "${spring.kafka.consumer.group-id}")
//    public void handleMessageChangeCardStatus(RequestCardStatusDto requestCardStatusDto) {
//        cardService.changeCardStatus(
//                requestCardStatusDto.getClientId(),
//                requestCardStatusDto.getCardStatusIssueRequestDto());
//    }
//
//    @KafkaListener(id = "${LISTENER_ID_CHANGE_DEPOSIT_RENEWAL_STATUS}", topics = {"${CHANGE_DEPOSIT_RENEWAL_TOPIC}"}, groupId = "${spring.kafka.consumer.group-id}")
//    public void handleMessageChangeDepositRenewalStatus(AutoRenewalDepositDto autoRenewalDepositDto) throws AgreementNotActiveException {
//        depositActionService.changeAutoRenewalAgreementStatus(autoRenewalDepositDto);
//    }
//    @KafkaListener(id = "${LISTENER_ID_CHANGE_DEPOSIT_REVOCTION_TOPIC}", topics = {"${CHANGE_DEPOSIT_REVOCATION_TOPIC}"}, groupId = "${spring.kafka.consumer.group-id}")
//    public void handleMessageChangeDepositWithdrawalStatus(RequestWithdrawalDepositDto requestWithdrawalDepositDto) {
//        depositActionService.changeWithdrawalAgreementStatusAndCreateOperation(requestWithdrawalDepositDto.getAgreementId(),
//                requestWithdrawalDepositDto.getWithdrawalDepositDto());
//    }
}