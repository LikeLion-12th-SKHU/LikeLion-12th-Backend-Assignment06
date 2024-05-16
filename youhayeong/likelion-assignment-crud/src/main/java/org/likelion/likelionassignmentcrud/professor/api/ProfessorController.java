package org.likelion.likelionassignmentcrud.professor.api;

import org.likelion.likelionassignmentcrud.professor.api.dto.request.ProfessorSaveReqDto;
import org.likelion.likelionassignmentcrud.professor.api.dto.request.ProfessorUpdateReqDto;
import org.likelion.likelionassignmentcrud.professor.api.dto.response.ProfessorInfoResDto;
import org.likelion.likelionassignmentcrud.professor.api.dto.response.ProfessorListResDto;
import org.likelion.likelionassignmentcrud.professor.application.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/save")   //새 교수 등록 / requestBody <= 서버에 전달하려는 데이터
    public ResponseEntity<String> professorSave(@RequestBody ProfessorSaveReqDto professorSaveReqDto) {
        professorService.professorSave(professorSaveReqDto); //professorservice의 professorsave 메서드 호출해서 교수 저장
        return new ResponseEntity<>("교수 저장!", HttpStatus.CREATED); //저장 성공(http 응답) 리턴
    }

    @GetMapping("/professors")     //모든 교수 조회(request body 필요 x = 전달할 데이터가 없기에
    public ResponseEntity<ProfessorListResDto> professorFindAll() {
        ProfessorListResDto professorListResDto = professorService.professorFindAll();  //professorservice의 findall 메소드 호출해서 전체 교수(리스트) 조회
        return new ResponseEntity<>(professorListResDto, HttpStatus.OK);   //조회된 리스트 리턴
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorInfoResDto> professorFindOne(@PathVariable("professorId") Long professorId) {
        ProfessorInfoResDto professorInfoResDto = professorService.professorFindOne(professorId);  //professorservice의 findone 메소드 호출해서 특정 교수 조회
        return new ResponseEntity<>(professorInfoResDto, HttpStatus.OK); //조회된 교수 정보 리턴
    }

    // update
    @PatchMapping("/{professorId}")
    public ResponseEntity<String> professorUpdate(@PathVariable("professorId") Long professorId,
                                                  @RequestBody ProfessorUpdateReqDto professorUpdateReqDto) {
        professorService.professorUpdate(professorId, professorUpdateReqDto);
        return new ResponseEntity<>("교수 수정!", HttpStatus.OK);
    }

    // delete
    @DeleteMapping("{professorId}")
    public ResponseEntity<String> professorDelete(@PathVariable("professorId") Long professorId) {
        professorService.professorDelete(professorId);
        return new ResponseEntity<>("교수 삭제!", HttpStatus.OK);
    }
}
