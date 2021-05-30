package com.renting.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	boolean existsByName(String name);
	boolean existsByEmail(String email);
	boolean existsByPhone(String phone);
	UserEntity findByUsername(String username);
}
