package org.likelion.likelionassignmentcrud.student.api.dto.request;

import org.likelion.likelionassignmentcrud.student.domain.StudentGrade;

public record StudentSaveReqDto(
        String name,
        int age,
        Long studentId,
        StudentGrade stuGrade
) {

}
