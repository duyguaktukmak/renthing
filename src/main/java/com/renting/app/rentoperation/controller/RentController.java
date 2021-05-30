package com.renting.app.rentoperation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renting.app.rentoperation.dto.FinishRentDto;
import com.renting.app.rentoperation.dto.RentOfferResultDto;
import com.renting.app.rentoperation.dto.RentRequestDto;
import com.renting.app.rentoperation.service.RentOperationService;

import javassist.NotFoundException;

@RequestMapping("rent")
@RestController
public class RentController {

	@Autowired
	private RentOperationService rentService;

	@PostMapping("/rentRequest")
	public Long createRentRequest(@RequestBody RentRequestDto rentRequestDto) throws NotFoundException {
		return rentService.createRentRequest(rentRequestDto);
	}

	@PostMapping("/resultRentOffer")
	public void resultRentOffer(@RequestBody RentOfferResultDto rentOfferResultDto) {
		rentService.resultRentOffer(rentOfferResultDto);
	}

	@PostMapping("/finishRent")
	public void finishRent(@RequestBody FinishRentDto finishRentDto) throws NotFoundException {
		rentService.finishRent(finishRentDto);
	}
}
