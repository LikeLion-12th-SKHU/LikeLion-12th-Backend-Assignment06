package org.likelion.likelionassignmentcrud.professor.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.professor.domain.Department;
import org.likelion.likelionassignmentcrud.professor.domain.Professor;

@Builder
public record ProfessorInfoResDto(  // 이름, 나이, 학과
        String name,
        int age,
        Department department
) {
    public static ProfessorInfoResDto from(Professor professor) {
        return ProfessorInfoResDto.builder()
                .name(professor.getName())
                .age(professor.getAge())
                .department(professor.getDepartment())
                .build();
    }
}