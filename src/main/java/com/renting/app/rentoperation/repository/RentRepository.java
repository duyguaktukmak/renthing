package com.renting.app.rentoperation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renting.app.common.RentState;

public interface RentRepository extends JpaRepository<RentEntity, Long> {
	List<RentEntity> findRentByUser_Username(String username);

	List<RentEntity> findRentByState(RentState state);

	List<RentEntity> findRentByUser_UsernameAndState(String username, RentState state);

}
