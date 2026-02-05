package com.mutsat.www.recruitment.controller.dto.response;

import com.mutsat.www.recruitment.domain.CompanyRecruitment;

import lombok.Builder;

@Builder
public record RecruitmentUpdateResponseDto(Long id, String position, long recruitmentCompensation, String content, String skills) {
	public static RecruitmentUpdateResponseDto from(CompanyRecruitment recruitmentNotice){
		return RecruitmentUpdateResponseDto.builder()
			.id(recruitmentNotice.getId())
			.position(recruitmentNotice.getPosition())
			.skills(recruitmentNotice.getSkills().getSkill())
			.recruitmentCompensation(recruitmentNotice.getRecruitmentCompensation())
			.content(recruitmentNotice.getContent())
			.build();
	}
}
