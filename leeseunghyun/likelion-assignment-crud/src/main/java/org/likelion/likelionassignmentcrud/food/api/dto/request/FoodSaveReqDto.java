package org.likelion.likelionassignmentcrud.food.api.dto.request;

import org.springframework.data.repository.query.parser.Part;

public record FoodSaveReqDto(
        String name,
        int price,
        String type
) {
}
