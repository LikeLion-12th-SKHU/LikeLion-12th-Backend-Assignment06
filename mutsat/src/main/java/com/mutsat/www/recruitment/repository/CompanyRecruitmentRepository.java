package com.mutsat.www.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsat.www.recruitment.domain.CompanyRecruitment;

public interface CompanyRecruitmentRepository extends JpaRepository<CompanyRecruitment, Long> {
}
