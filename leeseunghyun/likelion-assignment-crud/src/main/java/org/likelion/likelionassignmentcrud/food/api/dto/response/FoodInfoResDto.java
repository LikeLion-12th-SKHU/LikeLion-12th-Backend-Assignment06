package org.likelion.likelionassignmentcrud.food.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.food.domain.Food;

@Builder
public record FoodInfoResDto(
        String name,
        int price,
        String type
) {
    public static FoodInfoResDto from(Food food) {
        return FoodInfoResDto.builder()
                .name(food.getName())
                .price(food.getPrice())
                .type(food.getType())
                .build();
    }
}
