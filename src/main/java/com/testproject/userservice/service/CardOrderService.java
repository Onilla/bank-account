package com.testproject.userservice.service;


import com.testproject.userservice.DTO.RequestCardDto;

import java.util.UUID;


public interface CardOrderService {
    /**
     * handleRequestForeNewCard - заявка на открытие дебетовой карты
     * <p>
     * // * @param clientId            - идентификатор клиента
     * //* @param cardIssueRequestDto - идентификатор дебетового карточного продукта
     *
     * @return requestCardDto с параметрами clientId,cardIssueRequestDto
     */

    RequestCardDto handleRequestForNewCard(UUID clientId);

    /**
     * createCard - метод создания дебетовой карты с необходимыми полями
     * <p>
     * //@param clientIdn- идентификатор клиента
     */
    void createCard(UUID clientId);
}
//