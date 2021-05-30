package com.renting.app.rentoperation.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentRequestDto {

	private String userName;
	private Long itemId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private float price;
	private String comment;
}
