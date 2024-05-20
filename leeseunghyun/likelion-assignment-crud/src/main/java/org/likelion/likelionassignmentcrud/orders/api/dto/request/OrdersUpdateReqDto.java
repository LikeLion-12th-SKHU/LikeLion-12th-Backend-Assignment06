package org.likelion.likelionassignmentcrud.orders.api.dto.request;

public record OrdersUpdateReqDto(
        String shippingAddress,
        String paymentInfo
) {
}
