package com.renting.app.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {

	private String username;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String address;
	private String creditCardId;
}
