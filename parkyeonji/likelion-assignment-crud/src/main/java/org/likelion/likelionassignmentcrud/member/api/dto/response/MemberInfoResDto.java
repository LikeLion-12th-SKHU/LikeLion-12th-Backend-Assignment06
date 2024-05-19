package org.likelion.likelionassignmentcrud.member.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.member.domain.Member;

@Builder
public record MemberInfoResDto(
        String name,
        int age,
        String clubName // 수정
) {
    public static MemberInfoResDto from(Member member) {
        return MemberInfoResDto.builder()
                .name(member.getName())
                .age(member.getAge())
                .clubName(member.getClub().getName()) // 수정
                .build();
    }
}
