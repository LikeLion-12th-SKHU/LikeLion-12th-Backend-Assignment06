package com.mutsat.www.recruitment.controller.dto.response;

import com.mutsat.www.recruitment.domain.CompanyRecruitment;

import lombok.Builder;

@Builder
public record RecruitmentDto(long id, String companyName, String country, String region, String position, long recruitmentCompensation, String skills) {
	public static RecruitmentDto from(CompanyRecruitment companyRecruitment){
		return RecruitmentDto.builder()
			.id(companyRecruitment.getId())
			.companyName(companyRecruitment.getCompany().getCompanyName())
			.country(companyRecruitment.getCompany().getCountry())
			.region(companyRecruitment.getCompany().getRegion())
			.position(companyRecruitment.getPosition())
			.recruitmentCompensation(companyRecruitment.getRecruitmentCompensation())
			.skills(companyRecruitment.getSkills().getSkill())
			.build();
	}
}
