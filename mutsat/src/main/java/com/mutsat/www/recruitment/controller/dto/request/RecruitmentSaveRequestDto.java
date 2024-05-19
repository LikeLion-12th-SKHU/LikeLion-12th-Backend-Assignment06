package com.mutsat.www.recruitment.controller.dto.request;

import com.mutsat.www.member.domain.Skills;

public record RecruitmentSaveRequestDto(Long companyId, String position, long money, String content, Skills skills) {
}
