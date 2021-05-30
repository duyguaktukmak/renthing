package com.renting.app.item.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveWaitingItems {

	private int itemsCount;
	private List<ItemPreviewDto> items;
	private List<ApproveWaitingItem> approveWaitingItems;

}
