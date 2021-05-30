package com.renting.app.item.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableTimeDto {
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
