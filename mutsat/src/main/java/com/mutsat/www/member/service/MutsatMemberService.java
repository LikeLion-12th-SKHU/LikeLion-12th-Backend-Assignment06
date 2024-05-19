package com.mutsat.www.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutsat.www.member.controller.dto.request.MemberSaveRequestDto;
import com.mutsat.www.member.domain.MutsatMember;
import com.mutsat.www.member.repository.MutsatMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MutsatMemberService {
	private final MutsatMemberRepository memberRepository;

	@Transactional
	public void saveMember(MemberSaveRequestDto memberSaveRequestDto){
		memberRepository.save(MutsatMember.builder()
				.name(memberSaveRequestDto.name())
				.phoneNumber(memberSaveRequestDto.phoneNumber())
				.rank(memberSaveRequestDto.rank())
			.build());
	}


}
