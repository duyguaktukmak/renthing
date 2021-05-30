package com.renting.app.item.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveWaitingItem {

	private Long rentId;
	private Long itemId;
	private String itemName;
	private String offeredUserName;
	private float offeredPrice;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
