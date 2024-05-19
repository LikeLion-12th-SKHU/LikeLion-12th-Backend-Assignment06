package com.mutsat.www.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsat.www.company.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
