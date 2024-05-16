package org.likelion.likelionassignmentcrud.professor.domain.repository;

import org.likelion.likelionassignmentcrud.professor.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

//mybatis mapper에 해당하는 interface
public interface ProfessorRepository extends JpaRepository<Professor, Long> {  //pk의 타입(@id 붙은 변수의 타입)
}
