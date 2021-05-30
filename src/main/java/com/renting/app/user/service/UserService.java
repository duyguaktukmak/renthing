package com.renting.app.user.service;

import java.util.List;

import com.renting.app.user.dto.RegisterUserDto;
import com.renting.app.user.dto.UserDto;

public interface UserService {
	Long registerUser(RegisterUserDto registerUserDto);
	List<UserDto> getAllUsers();
	UserDto getUserByUserName(String userName);
}
