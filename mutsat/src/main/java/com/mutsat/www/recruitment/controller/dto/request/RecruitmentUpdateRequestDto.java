package com.mutsat.www.recruitment.controller.dto.request;

import com.mutsat.www.member.domain.Skills;

public record RecruitmentUpdateRequestDto(String position, Long money, String content, Skills skills) { //여기서는 money를 안보낼 수 있어 Long으로 설정
}
