package com.testproject.userservice.controller;
import com.testproject.userservice.service.CardOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/cards")
@Tag(name = "CardOrderController", description = "Этот контроллер служит для осуществления выпуска дебетовой карты для клиента")
public class CardOrderController {
    private final CardOrderService cardOrderService;

    @Operation(summary = "Позволяет отправить заявку на выпуск дебетовой карты")
    @PostMapping("/new")
    public ResponseEntity<Void> cardIssueOrder(@RequestParam
                                              // @Parameter(description = "идентификатор клиента")
                                               UUID clientId) {
        cardOrderService.handleRequestForNewCard(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}