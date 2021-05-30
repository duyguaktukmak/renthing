package com.renting.app.rentoperation.repository;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.renting.app.common.BaseEntity;
import com.renting.app.common.RentState;
import com.renting.app.item.repository.ItemEntity;
import com.renting.app.user.repository.UserEntity;

import lombok.Setter;
import lombok.Getter;

@Entity(name = "Rent")
@Getter
@Setter
public class RentEntity extends BaseEntity{

	@ManyToOne
	private UserEntity user;

	@ManyToOne
	private ItemEntity item;

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private float offeredPrice;
	private String comment;
	
	@Enumerated(EnumType.STRING)
	private RentState state;
}
