package com.mutsat.www.recruitment.domain;

import com.mutsat.www.member.domain.MutsatMember;
import com.mutsat.www.recruitment.domain.CompanyRecruitment;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private MutsatMember member;

	@ManyToOne(fetch = FetchType.LAZY)
	private CompanyRecruitment companyRecruitment;

	@Builder
	private RecruitmentStatus(MutsatMember member, CompanyRecruitment companyRecruitment) {
		this.member = member;
		this.companyRecruitment = companyRecruitment;
	}
}
