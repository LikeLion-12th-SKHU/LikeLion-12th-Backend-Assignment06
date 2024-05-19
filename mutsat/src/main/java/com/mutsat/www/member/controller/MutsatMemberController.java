package com.mutsat.www.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutsat.www.member.controller.dto.request.MemberSaveRequestDto;
import com.mutsat.www.member.service.MutsatMemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MutsatMemberController {
	private final MutsatMemberService mutsatMemberService;

	@PostMapping
	public ResponseEntity<String> saveMember(@RequestBody MemberSaveRequestDto memberSaveRequestDto){
		mutsatMemberService.saveMember(memberSaveRequestDto);
		return new ResponseEntity<>("유저 생성 완료", HttpStatus.CREATED);
	}
}
