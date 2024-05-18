package com.mutsat.www.recruitment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutsat.www.recruitment.controller.dto.response.ApplyResponseDto;
import com.mutsat.www.recruitment.controller.dto.response.RecruitmentDto;
import com.mutsat.www.recruitment.controller.dto.response.RecruitmentListDto;
import com.mutsat.www.recruitment.controller.dto.response.RecruitmentUpdateResponseDto;
import com.mutsat.www.recruitment.controller.dto.request.ApplyRequestDto;
import com.mutsat.www.recruitment.controller.dto.request.RecruitmentSaveRequestDto;
import com.mutsat.www.recruitment.controller.dto.request.RecruitmentUpdateRequestDto;
import com.mutsat.www.recruitment.service.CompanyRecruitmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recruitment")
@RequiredArgsConstructor
public class RecruitmentController {
	private final CompanyRecruitmentService companyRecruitmentService;

	@PostMapping
	public ResponseEntity<String> saveRecruitment(@RequestBody RecruitmentSaveRequestDto saveRequestDto){
		companyRecruitmentService.saveRecruitmentNotice(saveRequestDto);
		return new ResponseEntity<>("저장완료", HttpStatus.CREATED);
	}
	@PostMapping("/apply")
	public ResponseEntity<ApplyResponseDto> apply(@RequestBody ApplyRequestDto applyRequestDto){
		return new ResponseEntity<>(companyRecruitmentService.apply(applyRequestDto),HttpStatus.CREATED);
	}

	@PatchMapping("/{recruitmentId}")
	public ResponseEntity<RecruitmentUpdateResponseDto> updateRecruitment(@PathVariable(value = "recruitmentId") Long id, @RequestBody RecruitmentUpdateRequestDto updateRequestDto){
		return new ResponseEntity<>(companyRecruitmentService.updateRecruitmentNotice(id, updateRequestDto), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<RecruitmentListDto> findAllRecruitment(){
		return new ResponseEntity<>(companyRecruitmentService.findAllRecruitment(), HttpStatus.OK) ;
	}
	@GetMapping("/{recruit_id}")
	public ResponseEntity<RecruitmentDto> findAllRecruitment(@PathVariable(value = "recruit_id") Long id){
		return new ResponseEntity<>(companyRecruitmentService.findByRecruitmentId(id), HttpStatus.OK);
	}
}
