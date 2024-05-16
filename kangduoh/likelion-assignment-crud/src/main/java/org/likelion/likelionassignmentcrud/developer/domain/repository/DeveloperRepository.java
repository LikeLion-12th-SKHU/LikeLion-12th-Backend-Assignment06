package org.likelion.likelionassignmentcrud.developer.domain.repository;

import org.likelion.likelionassignmentcrud.developer.domain.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
