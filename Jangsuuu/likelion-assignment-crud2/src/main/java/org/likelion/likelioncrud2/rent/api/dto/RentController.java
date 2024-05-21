package org.likelion.likelioncrud2.rent.api.dto;

import org.likelion.likelioncrud2.rent.api.dto.request.RentSaveReqDto;
import org.likelion.likelioncrud2.rent.api.dto.request.RentUpdateReqDto;
import org.likelion.likelioncrud2.rent.api.dto.response.RentListResDto;
import org.likelion.likelioncrud2.rent.application.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    // 저장하기
    @PostMapping("/save")
    public ResponseEntity<String> rentSave(@RequestBody RentSaveReqDto rentSaveReqDto) {
        rentService.rentSave(rentSaveReqDto);
        return new ResponseEntity<>("대출 현황 저장!", HttpStatus.CREATED);
    }

    // 대출 현황 불러오기
    @GetMapping("/{studentId}")
    public ResponseEntity<RentListResDto> rentFindAll(@PathVariable("studentId") Long studentId) {
        RentListResDto rentListResDto = rentService.rentFindAll(studentId);
        return new ResponseEntity<>(rentListResDto, HttpStatus.OK);
    }

    // 대출 현황 수정하기
    @PatchMapping("/{studentId}")
    public ResponseEntity<String> rentUpdate(@PathVariable("studentId") Long rentId, @RequestBody RentUpdateReqDto rentUpdateReqDto) {
        rentService.rentUpdate(rentId, rentUpdateReqDto);
        return new ResponseEntity<>("대출 현황 수정 완료!", HttpStatus.OK);
    }

    @DeleteMapping("/{rentId}")
    public ResponseEntity<String> rentDelete(@PathVariable("rentId") Long rentId) {
        rentService.rentDelete(rentId);
        return new ResponseEntity<>("대출 현황 삭제 완료!", HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/Delete")
    public ResponseEntity<String> rentDeleteAll(@PathVariable("studentId") Long studentId) {
        rentService.rentDeleteAll(studentId);
        return new ResponseEntity<>("전체 삭제 완료!", HttpStatus.OK);
    }
}
