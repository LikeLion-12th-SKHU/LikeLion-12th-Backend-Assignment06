package org.likelion.likelionassignmentcrud.club.api.dto;

import org.likelion.likelionassignmentcrud.club.api.dto.request.ClubSaveReqDto;
import org.likelion.likelionassignmentcrud.club.api.dto.request.ClubUpdateReqDto;
import org.likelion.likelionassignmentcrud.club.api.dto.response.ClubInfoResDto;
import org.likelion.likelionassignmentcrud.club.api.dto.response.ClubListResDto;
import org.likelion.likelionassignmentcrud.club.application.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> clubSave(@RequestBody ClubSaveReqDto clubSaveReqDto) {
        clubService.clubSave(clubSaveReqDto);
        return new ResponseEntity<>("동아리 저장 완료", HttpStatus.CREATED);
    }

    @GetMapping("/clubs")
    public ResponseEntity<ClubListResDto> clubFindAll() {
        ClubListResDto clubListResDto = clubService.clubFindAll();
        return new ResponseEntity<>(clubListResDto, HttpStatus.OK);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<ClubInfoResDto> clubFindOne(@PathVariable("clubId") Long clubId) {
        ClubInfoResDto clubInfoResDto = clubService.clubFindOne(clubId);
        return new ResponseEntity<>(clubInfoResDto, HttpStatus.OK);
    }

    // 추가
    @PatchMapping("/{clubId}")
    public ResponseEntity<String> clubUpdate(@PathVariable("clubId") Long clubId,
                                             @RequestBody ClubUpdateReqDto clubUpdateReqDto) {
        clubService.clubUpdate(clubId, clubUpdateReqDto);
        return new ResponseEntity<>("동아리 수정", HttpStatus.OK);
    }
    @DeleteMapping("/{clubId}")
    public ResponseEntity<String> clubDelete(@PathVariable("clubId") Long clubId) {
        clubService.clubDelete(clubId);
        return new ResponseEntity<>("동아리 삭제", HttpStatus.OK);
    }
}
