package com.renting.app.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemDto {

	private String name;
	private String description;
	private float price;
	private String[] imagePathList;
	private String userName;
}
