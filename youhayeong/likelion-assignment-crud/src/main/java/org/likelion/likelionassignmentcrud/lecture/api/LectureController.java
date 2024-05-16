package org.likelion.likelionassignmentcrud.lecture.api;

import org.likelion.likelionassignmentcrud.lecture.api.dto.request.LectureSaveReqDto;
import org.likelion.likelionassignmentcrud.lecture.api.dto.request.LectureUpdateReqDto;
import org.likelion.likelionassignmentcrud.lecture.api.dto.response.LectureListResDto;
import org.likelion.likelionassignmentcrud.lecture.application.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> lectureSave(@RequestBody LectureSaveReqDto lectureSaveReqDto) {
        lectureService.lectureSave(lectureSaveReqDto);
        return new ResponseEntity<>("강의 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<LectureListResDto> myLectureFindAll(@PathVariable("professorId") Long professorId) {
        LectureListResDto lectureListResDto = lectureService.lectureFindProfessor(professorId);
        return new ResponseEntity<>(lectureListResDto, HttpStatus.OK);
    }

    //update
    @PatchMapping("{lectureId}")
    public ResponseEntity<String> lectureUpdate(@PathVariable("lectureId") Long lectureId,
                                                @RequestBody LectureUpdateReqDto lectureUpdateReqDto) {
        lectureService.lectureUpdate(lectureId, lectureUpdateReqDto);
        return new ResponseEntity<>("강의 수정!", HttpStatus.OK);
    }

    //delete
    @DeleteMapping("{lectureId}")
    public ResponseEntity<String> lectureDelete(@PathVariable("lectureId") Long lectureId) {
        lectureService.lectureDelete(lectureId);
        return new ResponseEntity<>("강의 삭제!", HttpStatus.OK);
    }
}
