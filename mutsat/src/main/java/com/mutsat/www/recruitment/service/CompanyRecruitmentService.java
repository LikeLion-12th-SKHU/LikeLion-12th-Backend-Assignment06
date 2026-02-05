package com.mutsat.www.recruitment.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutsat.www.recruitment.controller.dto.response.ApplyResponseDto;
import com.mutsat.www.recruitment.controller.dto.response.RecruitmentDto;
import com.mutsat.www.recruitment.controller.dto.response.RecruitmentListDto;
import com.mutsat.www.recruitment.controller.dto.request.RecruitmentSaveRequestDto;
import com.mutsat.www.recruitment.controller.dto.request.RecruitmentUpdateRequestDto;
import com.mutsat.www.recruitment.controller.dto.response.RecruitmentUpdateResponseDto;
import com.mutsat.www.recruitment.controller.dto.request.ApplyRequestDto;
import com.mutsat.www.company.domain.Company;
import com.mutsat.www.recruitment.domain.CompanyRecruitment;
import com.mutsat.www.member.domain.MutsatMember;
import com.mutsat.www.recruitment.domain.RecruitmentStatus;
import com.mutsat.www.recruitment.repository.CompanyRecruitmentRepository;
import com.mutsat.www.recruitment.repository.CompanyRecruitmentStatusRepository;
import com.mutsat.www.company.repository.CompanyRepository;
import com.mutsat.www.member.repository.MutsatMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
//crud -> 생성, 조회 -> 조회일 때만 쓰는거., 수정, 삭제가 있어요.
public class CompanyRecruitmentService {
	private final MutsatMemberRepository memberRepository;
	private final CompanyRecruitmentRepository companyRecruitmentRepository;
	private final CompanyRecruitmentStatusRepository statusRepository;
	private final CompanyRepository companyRepository;

	/**
	 * @params:
	 */
	@Transactional
	public void saveRecruitmentNotice(RecruitmentSaveRequestDto recruitmentRequestDto) {
		Company company = companyRepository.findById(recruitmentRequestDto.companyId()).orElseThrow();
		CompanyRecruitment recruitmentNotice = CompanyRecruitment.builder() //자바에서만
			.company(company)
			.position(recruitmentRequestDto.position())
			.recruitmentCompensation(recruitmentRequestDto.money())
			.content(recruitmentRequestDto.content())
			.skills(recruitmentRequestDto.skills())
			.build();
		companyRecruitmentRepository.save(recruitmentNotice);
	}

	@Transactional //회사측에서 자신이 모집공고를 올렸는데 이거를 어, 백엔드개발자를 뽑을라햇는데 프론트로 올렷네 -> 고치고싶다.
	public RecruitmentUpdateResponseDto updateRecruitmentNotice(Long id,RecruitmentUpdateRequestDto updateRequestDto) {
		CompanyRecruitment recruitmentNotice = companyRecruitmentRepository.findById(id)
			.orElseThrow(IllegalAccessError::new);
		return RecruitmentUpdateResponseDto.from(nullcheckAndUpdateRequestDto(recruitmentNotice, updateRequestDto));
	}

	@Transactional
	public ApplyResponseDto apply(ApplyRequestDto applyRequestDto){
		MutsatMember member = memberRepository.findById(applyRequestDto.memberId()).orElseThrow();
		CompanyRecruitment companyRecruitment = companyRecruitmentRepository.findById(applyRequestDto.recruitmentId()).orElseThrow();
		RecruitmentStatus recruitmentStatus = RecruitmentStatus.builder()
			.member(member)
			.companyRecruitment(companyRecruitment)
			.build();
		statusRepository.save(recruitmentStatus);
		return ApplyResponseDto.of(recruitmentStatus.getMember().getId(), recruitmentStatus.getCompanyRecruitment().getId());
	}

	@Transactional
	public void withdrawApply(Long recruitmentId, Long memberId){
		MutsatMember member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
		CompanyRecruitment recruitment = companyRecruitmentRepository.findById(recruitmentId).orElseThrow(IllegalArgumentException::new);
		statusRepository.deleteByMemberAndCompanyRecruitment(member,recruitment);
	}

	public RecruitmentListDto findAllRecruitment() {
		return RecruitmentListDto.from(companyRecruitmentRepository.findAll()
				.stream()
				.map(RecruitmentDto::from)
				.collect(Collectors.toList()));
	}

	public RecruitmentDto findByRecruitmentId(Long id) {
		CompanyRecruitment companyRecruitment = companyRecruitmentRepository.findById(id).orElseThrow();
		return RecruitmentDto.from(companyRecruitment);
	}

	private CompanyRecruitment nullcheckAndUpdateRequestDto(CompanyRecruitment recruitmentNotice,
		RecruitmentUpdateRequestDto updateRequestDto) {
		if (updateRequestDto.content() != null) {
			recruitmentNotice.updateContent(updateRequestDto.content());
		}
		if (updateRequestDto.position() != null) {
			recruitmentNotice.updatePosition(updateRequestDto.position());
		}
		if (updateRequestDto.skills() != null && updateRequestDto.skills().getSkill() != null) {
			recruitmentNotice.updateSkills(updateRequestDto.skills());
		}
		if (updateRequestDto.money() != null) {
			recruitmentNotice.updateRecruitmentCompensation(updateRequestDto.money());
		}
		return recruitmentNotice;
	}
}
