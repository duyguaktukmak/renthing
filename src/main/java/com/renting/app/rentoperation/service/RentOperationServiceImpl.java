package com.renting.app.rentoperation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.app.common.RentOfferResult;
import com.renting.app.common.RentState;
import com.renting.app.item.dto.ApproveWaitingItem;
import com.renting.app.item.dto.ItemPreviewDto;
import com.renting.app.item.repository.ItemEntity;
import com.renting.app.item.service.ItemService;
import com.renting.app.rentoperation.dto.FinishRentDto;
import com.renting.app.rentoperation.dto.RentOfferResultDto;
import com.renting.app.rentoperation.dto.RentRequestDto;
import com.renting.app.rentoperation.repository.RentEntity;
import com.renting.app.rentoperation.repository.RentRepository;

import javassist.NotFoundException;

@Service
public class RentOperationServiceImpl implements RentOperationService {

	@Autowired
	private RentRepository rentRepository;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ModelMapper mapper;

	public Long createRentRequest(RentRequestDto rentRequestDto) throws NotFoundException {

		// TODO check if item is available
		ItemPreviewDto item = itemService.getItem(rentRequestDto.getItemId());
		var availableTimes = item.getAvailableTimes();
		for (var availableTime : availableTimes) {
			if (rentRequestDto.getStartTime() != null && rentRequestDto.getEndTime() != null) {
				if (rentRequestDto.getStartTime().isAfter(availableTime.getStartDate())
						&& rentRequestDto.getEndTime().isBefore(availableTime.getEndDate())) {

					RentEntity rentEntity = mapper.map(rentRequestDto, RentEntity.class);
					rentEntity.setState(RentState.OFFERED);
					var createdRentOffer = rentRepository.save(rentEntity);
					return createdRentOffer.getId();
				}
			}
		}
		return -1L;
	}

	@Override
	public void resultRentOffer(RentOfferResultDto rentOfferResultDto) {

		Optional<RentEntity> rentEntity = rentRepository.findById(rentOfferResultDto.getRentId());
		if (rentEntity.isPresent()) {
			
			if (rentOfferResultDto.getRentOfferResult().equals(RentOfferResult.ACCEPTED)) {
				rentEntity.get().setState(RentState.APPROVED);
				// RENTED state'e gecis?
			} else {
				rentEntity.get().setState(RentState.DENIED);
			}
		}
		rentRepository.save(rentEntity.get());
	}

	@Override
	public void finishRent(FinishRentDto finishRentDto) {
		Optional<RentEntity> rentEntity = rentRepository.findById(finishRentDto.getRentId());
		if (rentEntity.isPresent()) {
			rentEntity.get().setState(RentState.FINISHED);
		}
		rentRepository.save(rentEntity.get());
	}

	@Override
	public List<ItemEntity> getRentedItemsOfUserByUserName(String userName) {
		List<RentEntity> rentedItems = rentRepository.findRentByUser_Username(userName);
		return rentedItems.stream().map(rented -> rented.getItem()).collect(Collectors.toList());
	}

	@Override
	public List<ItemEntity> getItemsOfUserByUserNameAndState(String userName, RentState state) {
		List<RentEntity> rentedItems = rentRepository.findRentByUser_UsernameAndState(userName, state);
		return rentedItems.stream().map(rented -> rented.getItem()).collect(Collectors.toList());
	}

	@Override
	public List<ApproveWaitingItem> getApproveWaitingItemsByUserName(String userName) {
		List<RentEntity> rentedItems = rentRepository.findRentByState(RentState.OFFERED);
		var rentEntities = rentedItems.stream().filter(rented -> rented.getItem().getUser().getUsername().equals(userName))
				.collect(Collectors.toList());
		//List<ItemEntity> items = rentEntities.stream().map(rented -> rented.getItem()).collect(Collectors.toList());
		List<ApproveWaitingItem> result = new ArrayList<>();
	
		for(var rent: rentEntities) {
			ApproveWaitingItem approveItem = new ApproveWaitingItem();
			approveItem.setRentId(rent.getId());
			approveItem.setEndDate(rent.getEndTime());
			approveItem.setStartDate(rent.getStartTime());
			approveItem.setOfferedPrice(rent.getOfferedPrice());
			approveItem.setOfferedUserName(rent.getUser().getName());
			approveItem.setItemId(rent.getItem().getId());
			approveItem.setItemName(rent.getItem().getName());
			result.add(approveItem);
		}
		
		return result;
	}
}
