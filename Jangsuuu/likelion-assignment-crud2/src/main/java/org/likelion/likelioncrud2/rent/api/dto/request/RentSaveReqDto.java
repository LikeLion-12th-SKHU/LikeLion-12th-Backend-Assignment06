package org.likelion.likelioncrud2.rent.api.dto.request;

public record RentSaveReqDto(Long studentId, Long RentId, String rentTime, String returnTime, String bookName) {

}
