package org.likelion.likelionassignmentcrud.member.api.dto;

import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberInfoResDto;
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

        return new ResponseEntity<>("사업자 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/members")
    public ResponseEntity<MemberListResDto> memberFindAll() {
        MemberListResDto memberListResDto = memberService.memberFindAll();

        return new ResponseEntity<>(memberListResDto, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfoResDto> memberFindOne(@PathVariable("memberId") Long memberId) {
        MemberInfoResDto memberInfoResDto = memberService.memberFindOne(memberId);

        return new ResponseEntity<>(memberInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<String> memberUpdate(@PathVariable("memberId") Long memberId, @RequestBody MemberUpdateReqDto memberUpdateReqDto) {
        memberService.memberUpdate(memberId, memberUpdateReqDto);

        return new ResponseEntity<>("사업자 정보 수정!", HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> memberDelete(@PathVariable("memberId") Long memberId) {
        memberService.memberDelete(memberId);
        return new ResponseEntity<>("사업자 삭제!", HttpStatus.OK);
    }
}
