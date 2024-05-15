package org.likelion.likelionassignmentcrud.orders.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record OrdersListResDto(
        List<OrdersInfoResDto>  ordersList
) {
    public static OrdersListResDto from(List<OrdersInfoResDto> ordersList) {
        return OrdersListResDto.builder()
                .ordersList(ordersList)
                .build();
    }
}
