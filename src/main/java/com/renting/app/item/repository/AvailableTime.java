package com.renting.app.item.repository;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AvailableTime {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private ItemEntity item;
	
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
