package org.likelion.likelionassignmentcrud.student.application;

import lombok.AllArgsConstructor;
import org.likelion.likelionassignmentcrud.major.api.dto.request.MajorUpdateReqDto;
import org.likelion.likelionassignmentcrud.major.domain.Major;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentSaveReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentUpdateReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.response.StudentInfoResDto;
import org.likelion.likelionassignmentcrud.student.api.dto.response.StudentListResDto;
import org.likelion.likelionassignmentcrud.student.domain.Student;
import org.likelion.likelionassignmentcrud.student.domain.StudentGrade;
import org.likelion.likelionassignmentcrud.student.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.*;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public void studentSave(StudentSaveReqDto studentSaveReqDto) {
        Student student = Student.builder()
                .name(studentSaveReqDto.name())
                .age(studentSaveReqDto.age())
                .studentGrade(studentSaveReqDto.stuGrade())
                .build();

        studentRepository.save(student);
    }

    public StudentListResDto studentFindAll() {
        List<Student> student = studentRepository.findAll();

        List<StudentInfoResDto> studentInfoResDtoList = student.stream()
                .map(StudentInfoResDto::from)
                .toList();

        return StudentListResDto.from(studentInfoResDtoList);
    }

    public StudentInfoResDto studentFindOne(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(IllegalArgumentException::new);

        return StudentInfoResDto.from(student);
    }

    @Transactional
    public void studentUpdate(Long studentId, StudentUpdateReqDto studentUpdateReqDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);

        student.update(studentUpdateReqDto);
    }

    @Transactional
    public void StudentDelete(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(IllegalArgumentException::new);

        studentRepository.delete(student);
    }
}