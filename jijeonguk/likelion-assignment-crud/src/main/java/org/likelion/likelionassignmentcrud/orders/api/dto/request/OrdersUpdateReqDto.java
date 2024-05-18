package org.likelion.likelionassignmentcrud.orders.api.dto.request;

public record OrdersUpdateReqDto(
        String address,
        Long price
) {
}
