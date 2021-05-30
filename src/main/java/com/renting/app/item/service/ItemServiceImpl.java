package com.renting.app.item.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.app.common.ItemState;
import com.renting.app.common.RentState;
import com.renting.app.item.dto.ApproveWaitingItem;
import com.renting.app.item.dto.CreateItemDto;
import com.renting.app.item.dto.ItemListDto;
import com.renting.app.item.dto.ItemPreviewDto;
import com.renting.app.item.repository.ItemEntity;
import com.renting.app.item.repository.ItemRepository;
import com.renting.app.rentoperation.service.RentOperationService;
import com.renting.app.user.dto.UserDto;
import com.renting.app.user.repository.UserEntity;
import com.renting.app.user.service.UserService;

import javassist.NotFoundException;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RentOperationService rentService;

	@Autowired
	private UserService userService;

	@Override
	public ItemListDto getAllItems() {
		List<ItemEntity> items = itemRepository.findByState(ItemState.AVAILABLE);
		return this.mapToResult(items);
	}

	@Override
	public ItemListDto getItemsByUserName(String userName) {
		List<ItemEntity> items = itemRepository.findItemByUser_Username(userName);
		return this.mapToResult(items);
	}

	@Override
	public ItemListDto getRentedItemsOfUserByUserName(String userName) {
		List<ItemEntity> items = rentService.getRentedItemsOfUserByUserName(userName);
		return this.mapToResult(items);
	}

	@Override
	public ItemPreviewDto getItem(Long id) throws NotFoundException {
		Optional<ItemEntity> item = itemRepository.findById(id);
		if (item.isPresent()) {
			List<UserDto> users = userService.getAllUsers();
			var user = users.stream().filter(u -> u.getId() == item.get().getUser().getId()).findFirst().get();
			
			var result = mapper.map(item.get(), ItemPreviewDto.class);
			result.setUserName(user.getName());
			result.setUserUserName(user.getUsername());
			return result;
		}

		
		// TODO
		throw new NotFoundException("Item not found");
	}

	@Override
	public Long addItem(CreateItemDto createItemDto, String userName) {
		ItemEntity newItem = new ItemEntity();
		newItem.setName(createItemDto.getName());
		newItem.setDescription(createItemDto.getDescription());
		newItem.setPrice(createItemDto.getPrice());
		newItem.setState(ItemState.AVAILABLE);
		newItem.setCreatedBy(userName);
		newItem.setUser(mapper.map(userService.getUserByUserName(userName), UserEntity.class));
		// TODO
		// imagePath, available times

		var createdItem = itemRepository.save(newItem);
		return createdItem.getId();
	}

	private ItemListDto mapToResult(List<ItemEntity> items) {

		java.lang.reflect.Type targetListType = new TypeToken<List<ItemPreviewDto>>() {
		}.getType();

		List<ItemPreviewDto> itemList = mapper.map(items, targetListType);

		List<UserDto> users = userService.getAllUsers();
		for (var item : itemList) {
			var user = users.stream().filter(u -> u.getName() == item.getUserName()).findFirst().get();
			item.setUserName(user.getName());
			item.setUserUserName(user.getUsername());
		}

		var result = new ItemListDto();
		result.setItems(itemList);
		result.setItemsCount(itemList.size());

		return result;
	}

	@Override
	public List<ApproveWaitingItem> getApproveWaitingItems(String userName) {
		return rentService.getApproveWaitingItemsByUserName(userName);
	}

	@Override
	public ItemListDto getOfferedItems(String userName) {
		System.out.println(userName);
		List<ItemEntity> items = rentService.getItemsOfUserByUserNameAndState(userName, RentState.OFFERED);
		return this.mapToResult(items);
	}
}
