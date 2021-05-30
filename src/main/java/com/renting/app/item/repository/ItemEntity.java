package com.renting.app.item.repository;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.renting.app.common.BaseEntity;
import com.renting.app.common.ItemState;
import com.renting.app.user.repository.UserEntity;

import lombok.Setter;
import lombok.Getter;

@Entity(name = "Item")
@Getter
@Setter
public class ItemEntity extends BaseEntity {

	private String name;
	private String description;
	private float price;
	private String imagePath;

	@Enumerated(EnumType.STRING)
	private ItemState state;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
	private List<AvailableTime> availableTimes;

	@ManyToOne
	private UserEntity user;

	private String tags;
}
