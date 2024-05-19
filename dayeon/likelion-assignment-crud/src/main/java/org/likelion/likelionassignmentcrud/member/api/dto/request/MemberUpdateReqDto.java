package org.likelion.likelionassignmentcrud.member.api.dto.request;

public record MemberUpdateReqDto(
        String name,
        Long bizNum,
        String companyName
) {
}
