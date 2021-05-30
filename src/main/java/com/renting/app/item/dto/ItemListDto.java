package com.renting.app.item.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemListDto {

	private int itemsCount;
	private List<ItemPreviewDto> items;
}
