package org.likelion.likelionassignmentcrud.orders.api.dto.request;

public record OrdersSaveReqDto(
        // 사용자 아이디, 제목, 내용
        Long productId,
        String costName,
        String location
) {
}
