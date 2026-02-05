package com.mutsat.www.company.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutsat.www.company.controller.dto.request.CompanySaveRequestDto;
import com.mutsat.www.company.service.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

	private final CompanyService companyService;
	@PostMapping
	public ResponseEntity<String> saveCompany(@RequestBody CompanySaveRequestDto companySaveRequestDto){
		companyService.saveCompany(companySaveRequestDto);
		return new ResponseEntity<>("저장 완료", HttpStatus.CREATED);
	}
}
