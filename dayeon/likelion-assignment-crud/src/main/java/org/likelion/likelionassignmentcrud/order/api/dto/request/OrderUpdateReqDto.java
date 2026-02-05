package org.likelion.likelionassignmentcrud.order.api.dto.request;

import org.likelion.likelionassignmentcrud.order.domain.Item;

public record OrderUpdateReqDto(
        Long date,
        Item item,
        int quantity
) {
}
