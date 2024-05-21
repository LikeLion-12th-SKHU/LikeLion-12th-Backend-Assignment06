package org.likelion.likelioncrud2.student.api.dto.response;

import lombok.Builder;
import org.likelion.likelioncrud2.student.domain.Part;
import org.likelion.likelioncrud2.student.domain.Student;

@Builder
public record StudentInfoResDto(Long studentId, String name, Part part) {
    public static StudentInfoResDto from(Student student) {
        return StudentInfoResDto.builder()
                .studentId(student.getStudentId())
                .name(student.getName())
                .part(student.getPart())
                .build();
    }
}
