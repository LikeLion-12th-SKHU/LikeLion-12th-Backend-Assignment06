package org.likelion.likelionassignmentcrud.developer.api.dto.request;

import java.time.LocalDate;

public record DeveloperSaveReqDto(
        // 이름, 국가, 설립일
        String name,
        String country,
        LocalDate establishedDate
) {
}
