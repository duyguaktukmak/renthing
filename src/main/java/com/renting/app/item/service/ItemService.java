package com.renting.app.item.service;

import java.util.List;

import com.renting.app.item.dto.ApproveWaitingItem;
import com.renting.app.item.dto.CreateItemDto;
import com.renting.app.item.dto.ItemListDto;
import com.renting.app.item.dto.ItemPreviewDto;

import javassist.NotFoundException;

public interface ItemService {
	ItemListDto getAllItems();
	ItemListDto getItemsByUserName(String userName);
	ItemPreviewDto getItem(Long id) throws NotFoundException;
	ItemListDto getRentedItemsOfUserByUserName(String userName);
	Long addItem(CreateItemDto createItemDto, String userName);
	List<ApproveWaitingItem> getApproveWaitingItems(String userName);
	ItemListDto getOfferedItems(String userName);
}
