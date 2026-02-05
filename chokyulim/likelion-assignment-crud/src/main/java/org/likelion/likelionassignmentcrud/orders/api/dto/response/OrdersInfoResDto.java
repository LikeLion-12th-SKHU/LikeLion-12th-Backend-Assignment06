package org.likelion.likelionassignmentcrud.orders.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;

@Builder
public record OrdersInfoResDto(
        String costName,
        String location,
        String name
) {
    public static OrdersInfoResDto from(Orders orders) {
        return OrdersInfoResDto.builder()
                .costName(orders.getCostName())
                .location(orders.getLocation())
                .name(orders.getProduct().getName())
                .build();
    }
}
