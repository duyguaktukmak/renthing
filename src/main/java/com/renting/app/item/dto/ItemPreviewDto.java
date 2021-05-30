package com.renting.app.item.dto;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPreviewDto {

	private Long id;
	private String name;
	private String description;
	private float price;
	private String imagePath;
	private Date createdAt;
	private String userName;
	private String userUserName;
	private String userImagePath;
	private String tags;
	private List<AvailableTimeDto> availableTimes;

}
