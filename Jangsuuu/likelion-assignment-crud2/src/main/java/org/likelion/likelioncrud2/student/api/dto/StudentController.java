package org.likelion.likelioncrud2.student.api.dto;

import org.likelion.likelioncrud2.student.api.dto.request.StudentSaveReqDto;
import org.likelion.likelioncrud2.student.api.dto.request.StudentUpdateReqDto;
import org.likelion.likelioncrud2.student.api.dto.response.StudentInfoResDto;
import org.likelion.likelioncrud2.student.api.dto.response.StudentListResDto;
import org.likelion.likelioncrud2.student.application.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> studentSave(@RequestBody StudentSaveReqDto studentSaveReqDto) {
        studentService.studentSave(studentSaveReqDto);
        return new ResponseEntity<>("사용자 정보 저장(신규)!", HttpStatus.CREATED);
    }

    // 한명 불러오기
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentInfoResDto> studentFindOne(@PathVariable("studentId") Long studentId) {
        StudentInfoResDto studentInfoResDto = studentService.studentFindOne(studentId);
        return new ResponseEntity<>(studentInfoResDto, HttpStatus.OK);
    }

    // 전부 조회
    @GetMapping("/students")
    public ResponseEntity<StudentListResDto> studentFindAll() {
        StudentListResDto studentListResDto = studentService.studentFindAll();
        return new ResponseEntity<>(studentListResDto, HttpStatus.OK);
    }

    // 수정하기
    @PatchMapping("/{studentId}")
    public ResponseEntity<String> studentUpdate(@PathVariable("studentId")Long studentId,
                                             @RequestBody StudentUpdateReqDto studentUpdateReqDto) {
        studentService.studentUpdate(studentId, studentUpdateReqDto);
        return new ResponseEntity<>("사용자 정보 수정!", HttpStatus.OK);
    }

    // 삭제하기
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> studentDelete(@PathVariable("studentId")Long studentId) {
        studentService.studentDelete(studentId);
        return new ResponseEntity<>("사용자 정보 삭제!", HttpStatus.OK);
    }
}
