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
		CompanyRecruitment recruitmentNotice = CompanyRecruitment.builder()
			.company(company)
			.position(recruitmentRequestDto.content())
			.recruitmentCompensation(recruitmentRequestDto.money())
			.content(recruitmentRequestDto.content())
			.skills(recruitmentRequestDto.skills())
			.build();
		companyRecruitmentRepository.save(recruitmentNotice);
	}

	@Transactional
	public RecruitmentUpdateResponseDto updateRecruitmentNotice(Long id,RecruitmentUpdateRequestDto updateRequestDto) {
		CompanyRecruitment recruitmentNotice = companyRecruitmentRepository.findById(id)
			.orElseThrow();
		return RecruitmentUpdateResponseDto.from(nullcheckOfUpdateRequestDto(recruitmentNotice, updateRequestDto));
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

	private CompanyRecruitment nullcheckOfUpdateRequestDto(CompanyRecruitment recruitmentNotice,
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
