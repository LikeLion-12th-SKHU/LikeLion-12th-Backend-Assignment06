package org.likelion.likelioncrud2.student.application;

import org.likelion.likelioncrud2.student.api.dto.request.StudentSaveReqDto;
import org.likelion.likelioncrud2.student.api.dto.request.StudentUpdateReqDto;
import org.likelion.likelioncrud2.student.api.dto.response.StudentInfoResDto;
import org.likelion.likelioncrud2.student.api.dto.response.StudentListResDto;
import org.likelion.likelioncrud2.student.domain.Student;
import org.likelion.likelioncrud2.student.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 캡슐화된 상태 없이 모델에서 단독으로 독립된 인터페이스로 제공되는 작업(?)
@Service
// 실행 중 예외가 발생하면 해당 메서드를 실행하면서 수행한 쿼리들을 모두 롤백함.(읽기 전용)
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 생성
    @Transactional
    public void studentSave(StudentSaveReqDto studentSaveReqDto) {
        Student student = Student.builder()
                .name(studentSaveReqDto.name())
                .part(studentSaveReqDto.part()).build();

        studentRepository.save(student);
    }

    // 한명 불러오기
    public StudentInfoResDto studentFindOne(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다. ID : " + studentId));

        return StudentInfoResDto.from(student);
    }

    // 전부 불러오기
    public StudentListResDto studentFindAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentInfoResDto> studentInfoResDtoList = students.stream()
                .map(StudentInfoResDto::from).toList();

        return StudentListResDto.from(studentInfoResDtoList);
    }

    // 업데이트
    @Transactional
    public void studentUpdate(Long studentId, StudentUpdateReqDto studentUpdateReqDto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다. ID : " + studentId));

        student.updateStudentInfo(studentUpdateReqDto);
    }

    // 삭제
    @Transactional
    public void studentDelete(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다. ID : " + studentId));

        studentRepository.delete(student);
    }
}
