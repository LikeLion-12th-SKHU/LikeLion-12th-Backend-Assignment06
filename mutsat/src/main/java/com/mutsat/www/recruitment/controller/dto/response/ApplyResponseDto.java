package com.mutsat.www.recruitment.controller.dto.response;

import lombok.Builder;

@Builder
public record ApplyResponseDto(long memberId,long recruitmentId) {
	public static ApplyResponseDto of(long memberId, long recruitmentId){
		return ApplyResponseDto.builder()
			.memberId(memberId)
			.recruitmentId(recruitmentId)
			.build();
	}
}
