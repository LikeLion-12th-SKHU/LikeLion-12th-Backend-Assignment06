package org.likelion.likelionassignmentcrud.club.api.dto.request;

import org.likelion.likelionassignmentcrud.club.domain.Part; // 수정

public record ClubSaveReqDto(
        String name,
        String clubType,
        Part part // 수정
) {
}
