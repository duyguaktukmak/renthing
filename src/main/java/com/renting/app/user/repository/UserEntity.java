package com.renting.app.user.repository;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.renting.app.common.BaseEntity;
import com.renting.app.item.repository.ItemEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity {

	private String username;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String address;
	private String creditCardId;
	private String imagePath;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<ItemEntity> items;
}
