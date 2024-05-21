package com.testproject.userservice.DTO;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonAutoDetect
//@GroupSequence({CardDebitLimitDto.class, NotBlankPriority.class, PatternPriority.class})
public class RequestCardDto {
    //    @Pattern(regexp = ConstantRegExp.UUID,
//            message = ConstantRegExp.UUID_MESSAGE,
//            groups = PatternPriority.class)
//    @NotBlank(message = ConstantRegExp.CLIENT_ID_EMPTY,
//            groups = NotBlankPriority.class)
    private UUID clientId;

    //   private CardIssueRequestDto cardProductId;
}