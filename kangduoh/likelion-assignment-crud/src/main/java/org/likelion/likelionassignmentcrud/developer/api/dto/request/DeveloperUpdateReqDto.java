package org.likelion.likelionassignmentcrud.developer.api.dto.request;

public record DeveloperUpdateReqDto(
        String name,
        String country,
        String establishedDate
) {
}
