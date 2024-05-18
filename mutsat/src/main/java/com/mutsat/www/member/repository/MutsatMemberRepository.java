package com.mutsat.www.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsat.www.member.domain.MutsatMember;

public interface MutsatMemberRepository extends JpaRepository<MutsatMember, Long> {
}
