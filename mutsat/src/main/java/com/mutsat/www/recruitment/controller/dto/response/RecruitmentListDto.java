package com.mutsat.www.recruitment.controller.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record RecruitmentListDto(List<RecruitmentDto> dtoList) {
	public static RecruitmentListDto from(List<RecruitmentDto> dtoList){
		return RecruitmentListDto.builder()
			.dtoList(dtoList)
			.build();
	}
}
