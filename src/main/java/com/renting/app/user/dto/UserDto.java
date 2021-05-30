package com.renting.app.user.dto;

import java.util.List;

import com.renting.app.item.dto.ItemPreviewDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Long id;
	private String username;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String creditCardId;
	private List<ItemPreviewDto> itemList;
}
