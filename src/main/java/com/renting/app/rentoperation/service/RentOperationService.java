package com.renting.app.rentoperation.service;

import java.util.List;

import com.renting.app.common.RentState;
import com.renting.app.item.dto.ApproveWaitingItem;
import com.renting.app.item.repository.ItemEntity;
import com.renting.app.rentoperation.dto.FinishRentDto;
import com.renting.app.rentoperation.dto.RentOfferResultDto;
import com.renting.app.rentoperation.dto.RentRequestDto;

import javassist.NotFoundException;

public interface RentOperationService {
	Long createRentRequest(RentRequestDto rentRequestDto) throws NotFoundException;
	void resultRentOffer(RentOfferResultDto rentOfferResultDto);
	void finishRent(FinishRentDto finishRentDto);
	List<ItemEntity> getRentedItemsOfUserByUserName(String userName);
	List<ItemEntity> getItemsOfUserByUserNameAndState(String userName, RentState state);
	List<ApproveWaitingItem> getApproveWaitingItemsByUserName(String userName);
}
