package org.likelion.likelionassignmentcrud.member.api.dto.request;

public record MemberSaveReqDto(
        String name,
        Long bizNum,
        String companyName
) {
}
