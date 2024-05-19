package org.likelion.likelioncrud2.rent.api.dto.request;

public record RentSaveReqDto(Long userId, String rentTime, String returnTime, String bookName) {

}
