package org.likelion.likelionassignmentcrud.student.api;

import org.likelion.likelionassignmentcrud.major.api.dto.request.MajorUpdateReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentSaveReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.request.StudentUpdateReqDto;
import org.likelion.likelionassignmentcrud.student.api.dto.response.StudentInfoResDto;
import org.likelion.likelionassignmentcrud.student.api.dto.response.StudentListResDto;
import org.likelion.likelionassignmentcrud.student.application.StudentService;
import org.likelion.likelionassignmentcrud.student.domain.Student;
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
        return new ResponseEntity<>("학생 저장", HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<StudentListResDto> studentFindAll() {
        StudentListResDto studentListResDto = studentService.studentFindAll();
        return new ResponseEntity<>(studentListResDto, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentInfoResDto> studnetFindOne(@PathVariable("studentId") Long studentId) {
        StudentInfoResDto studentInfoResDto = studentService.studentFindOne(studentId);
        return new ResponseEntity<>(studentInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<String> studentUpdate(@PathVariable("studentId") Long studentId, @RequestBody StudentUpdateReqDto studentUpdateReqDto) {
        studentService.studentUpdate(studentId, studentUpdateReqDto);
        return new ResponseEntity<>("학생 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> studentDelete(@PathVariable("studentId") Long studentId) {
        studentService.StudentDelete(studentId);

        return new ResponseEntity<>("학생 삭제",HttpStatus.OK);
    }

}
