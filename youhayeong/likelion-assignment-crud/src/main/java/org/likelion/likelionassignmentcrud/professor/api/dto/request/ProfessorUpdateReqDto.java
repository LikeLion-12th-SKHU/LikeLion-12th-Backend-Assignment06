package org.likelion.likelionassignmentcrud.professor.api.dto.request;

import org.likelion.likelionassignmentcrud.professor.domain.Department;

public record ProfessorUpdateReqDto( //이름, 학과 update
        String name,
        Department department
) {
}
