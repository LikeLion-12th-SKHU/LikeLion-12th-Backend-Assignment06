package org.likelion.likelionassignmentcrud.member.api.dto;

import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberListResDto;
import org.likelion.likelionassignmentcrud.member.application.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> memberSave(@RequestBody MemberSaveReqDto memberSaveReqDto) {
        memberService.memberSave(memberSaveReqDto);
        return new ResponseEntity<>("동아리 회원 저장 완료", HttpStatus.CREATED);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<MemberListResDto> MemberFindAll(@PathVariable("clubId") Long clubId) {
        MemberListResDto memberListResDto = memberService.memberFindClub(clubId);
        return new ResponseEntity<>(memberListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<String> memberUpdate(@PathVariable("memberId") Long memberId,
                                               @RequestBody MemberUpdateReqDto memberUpdateReqDto) {
        memberService.memberUpdate(memberId, memberUpdateReqDto);
        return new ResponseEntity<>("동아리 회원 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> memberDelete(@PathVariable("memberId") Long memberId) {
        memberService.memberDelete(memberId);
        return new ResponseEntity<>("동아리 회원 삭제", HttpStatus.OK);
    }
}
