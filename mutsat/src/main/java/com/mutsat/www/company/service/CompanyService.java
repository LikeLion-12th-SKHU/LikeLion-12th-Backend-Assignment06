package com.mutsat.www.company.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutsat.www.company.controller.dto.request.CompanySaveRequestDto;
import com.mutsat.www.company.domain.Company;
import com.mutsat.www.company.repository.CompanyRepository;

@Service
public class CompanyService {
	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	/**
	 * @params:
	 */
	@Transactional
	public void saveCompany(CompanySaveRequestDto companySaveRequestDto){
		Company company = Company.builder()
			.companyName(companySaveRequestDto.companyName())
			.country(companySaveRequestDto.country())
			.region(companySaveRequestDto.region())
			.build();
		companyRepository.save(company);
	}
}
