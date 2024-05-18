package org.likelion.likelionassignmentcrud.orders.api.dto.request;

public record OrdersSaveReqDto(
        Long usersId,
        String address,
        Long price
) {
}
