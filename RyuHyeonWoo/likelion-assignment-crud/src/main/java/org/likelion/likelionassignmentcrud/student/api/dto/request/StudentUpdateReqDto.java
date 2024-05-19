package org.likelion.likelionassignmentcrud.student.api.dto.request;

import org.likelion.likelionassignmentcrud.student.domain.StudentGrade;

public record StudentUpdateReqDto(
        int age,
        StudentGrade stuGrade
) {
}
