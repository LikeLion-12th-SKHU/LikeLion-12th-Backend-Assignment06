package org.likelion.likelionassignmentcrud.developer.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.developer.domain.Developer;

@Builder
public record DeveloperInfoResDto(
        String developerName,
        String country,
        String establishedDate
) {
    public static DeveloperInfoResDto from(Developer developer) {
        return DeveloperInfoResDto.builder()
                .developerName(developer.getName())
                .country(developer.getCountry())
                .establishedDate(developer.getEstablishedDate())
                .build();
    }
}
