package org.likelion.likelionassignmentcrud.orders.api.dto.request;

public record OrdersSaveReqDto(

        String shippingAddress,
        String paymentInfo,
        Long foodId
) {
}