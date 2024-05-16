package org.likelion.likelionassignmentcrud.lecture.api.dto.request;

public record LectureSaveReqDto(
        Long professorId,
        String title,
        Long grade
) {
}