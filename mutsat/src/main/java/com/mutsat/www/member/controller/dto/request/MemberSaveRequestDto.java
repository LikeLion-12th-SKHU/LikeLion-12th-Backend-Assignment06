package com.mutsat.www.member.controller.dto.request;

import com.mutsat.www.member.domain.CareerRank;


public record MemberSaveRequestDto(String name, String phoneNumber, CareerRank rank) { //경력 없을경우. 하사.
}
