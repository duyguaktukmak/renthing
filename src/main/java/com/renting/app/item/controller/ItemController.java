package com.renting.app.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.renting.app.item.dto.ApproveWaitingItem;
import com.renting.app.item.dto.CreateItemDto;
import com.renting.app.item.dto.ItemListDto;
import com.renting.app.item.dto.ItemPreviewDto;
import com.renting.app.item.service.ItemService;

import javassist.NotFoundException;
import java.util.List;

@RequestMapping("items")
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public ItemListDto getItems() {
		return itemService.getAllItems();
	}

	@GetMapping("/filter")
	public ItemListDto getItemsByUserId(@RequestParam("userName") String userName) {
		return itemService.getItemsByUserName(userName);
	}

	@GetMapping("/feedRentedByUser")
	public ItemListDto feedRentedByUser(Authentication auth) {
		return itemService.getRentedItemsOfUserByUserName(auth.getName());
	}

	@GetMapping("/feedLeasedByUser")
	public ItemListDto feedLeasedByUser(Authentication auth) {
		return itemService.getItemsByUserName(auth.getName());
	}
	
	@GetMapping("/approveWaitingItems")
	public List<ApproveWaitingItem> itemsWaitingForApprove(Authentication auth) {
		return itemService.getApproveWaitingItems(auth.getName());
	}
	
	@GetMapping("/offeredItems")
	public ItemListDto itemsOfferedForApprove(Authentication auth) {
		return itemService.getOfferedItems(auth.getName());
	}

	@GetMapping("/{id}")
	public ItemPreviewDto getItem(@PathVariable Long id) throws NotFoundException {
		return itemService.getItem(id);
	}

	@PostMapping("/addItem")
	public Long addItem(Authentication auth, @RequestBody CreateItemDto createItemDto) {
		return itemService.addItem(createItemDto,
				createItemDto.getUserName() != null ? createItemDto.getUserName() : auth.getName());
	}

	@RequestMapping(path = "/addImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void addImagesToItem(@RequestParam("itemId") Long itemId, @RequestParam("file") MultipartFile file) {
		System.out.println(itemId);
		System.out.println(file.getOriginalFilename());
	}

	@PutMapping("/{id}")
	public void updateItem(@PathVariable String id) {

	}

	@DeleteMapping("/removeItem")
	public void removeItem() {

	}

}
