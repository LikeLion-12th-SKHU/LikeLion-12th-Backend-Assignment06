package org.likelion.likelionassignmentcrud.users.domain.repository;

import org.likelion.likelionassignmentcrud.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
