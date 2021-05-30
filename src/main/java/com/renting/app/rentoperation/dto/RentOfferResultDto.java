package com.renting.app.rentoperation.dto;

import com.renting.app.common.RentOfferResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentOfferResultDto {

	private Long rentId;
	private RentOfferResult rentOfferResult;
	private String comment;
}
