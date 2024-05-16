package org.likelion.likelionassignmentcrud.professor.api.dto.request;

import org.likelion.likelionassignmentcrud.professor.domain.Department;

//professor 저장 요청 dto 레코드(저장에 필요한 데이터 담고 있음
public record ProfessorSaveReqDto(     //이름, 나이, 학과 정보 받아 professorservice 클래스에서 professorsave
     String name,
     int age,
     Department department
) {
}
