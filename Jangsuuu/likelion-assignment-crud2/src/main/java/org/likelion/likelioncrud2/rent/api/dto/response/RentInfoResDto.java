package org.likelion.likelioncrud2.rent.api.dto.response;

import lombok.Builder;
import org.likelion.likelioncrud2.rent.domain.Rent;

@Builder
public record RentInfoResDto(Long rentId ,String rentTime, String returnTime, String bookName, String name) {
    public static RentInfoResDto from(Rent rent) {
        return RentInfoResDto.builder()
                .rentId(rent.getRentId())
                .rentTime(rent.getRentTime())
                .returnTime(rent.getReturnTime())
                .bookName(rent.getBookName())
                .name(rent.getStudent().getName())
                .build();
    }
}
