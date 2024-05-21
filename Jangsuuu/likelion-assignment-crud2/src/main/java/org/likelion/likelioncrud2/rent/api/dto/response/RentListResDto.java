package org.likelion.likelioncrud2.rent.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RentListResDto(List<RentInfoResDto> rents) {
    public static RentListResDto from(List<RentInfoResDto> rents) {
        return RentListResDto.builder().rents(rents).build();
    }
}
