package com.mutsat.www.recruitment.domain;

import java.util.ArrayList;
import java.util.List;

import com.mutsat.www.company.domain.Company;
import com.mutsat.www.member.domain.Skills;
import com.mutsat.www.recruitment.controller.dto.request.RecruitmentUpdateRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyRecruitment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Company company;

	@OneToMany(mappedBy = "companyRecruitment")
	private List<RecruitmentStatus> recruitmentStatusList = new ArrayList<>();

	private String position;

	private long recruitmentCompensation;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Enumerated(EnumType.STRING)
	private Skills skills;

	@Builder
	private CompanyRecruitment(Company company, String position, long recruitmentCompensation,
		String content, Skills skills) {
		this.company = company;
		this.position = position;
		this.recruitmentCompensation = recruitmentCompensation;
		this.content = content;
		this.skills = skills;
	}

	// public CompanyRecruitment updateRecruitment(RecruitmentUpdateRequestDto updateRequestDto){
	// 	this.position = updateRequestDto.position();
	// 	this.recruitmentCompensation = updateRequestDto.money();
	// 	this.skills = updateRequestDto.skills();
	// 	this.content = updateRequestDto.content();
	// 	return this;
	// }

	public void updatePosition(String position){
		this.position = position;
	}
	public void updateRecruitmentCompensation(long money){
		this.recruitmentCompensation = money;
	}
	public void updateSkills(Skills skills){
		this.skills = skills;
	}
	public void updateContent(String content){
		this.content = content;
	}


}
