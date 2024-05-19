package org.likelion.likelionassignmentcrud.member.api.dto.request;

public record MemberSaveReqDto(
        Long clubId,
        String name,
        int age
) {
}
