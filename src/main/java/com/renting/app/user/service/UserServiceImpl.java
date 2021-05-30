package com.renting.app.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.renting.app.user.dto.RegisterUserDto;
import com.renting.app.user.dto.UserDto;
import com.renting.app.user.repository.UserEntity;
import com.renting.app.user.repository.UserRepository;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public List<UserDto> getAllUsers() {

		List<UserEntity> users = userRepository.findAll();
		java.lang.reflect.Type targetListType = new TypeToken<List<UserDto>>() {
		}.getType();

		return mapper.map(users, targetListType);
	}

	public Long registerUser(RegisterUserDto registerUserDto) {

		var userWithSameName = userRepository.existsByName(registerUserDto.getName());
		if (userWithSameName) {
			// throw error
		}

		var userWithSameEmail = userRepository.existsByEmail(registerUserDto.getEmail());
		if (userWithSameEmail) {
			// throw error
		}

		var userWithSamePhone = userRepository.existsByPhone(registerUserDto.getPhone());
		if (userWithSamePhone) {
			// throw error
		}
		UserEntity newUser = new UserEntity();
		newUser.setName(registerUserDto.getName());
		newUser.setUsername(registerUserDto.getUsername());
		newUser.setEmail(registerUserDto.getEmail());
		newUser.setPhone(registerUserDto.getPhone());
		newUser.setAddress(registerUserDto.getAddress());
		
		//UserEntity newUser = mapper.map(registerUserDto, UserEntity.class);
		newUser.setPassword(bcryptEncoder.encode(registerUserDto.getPassword()));
		var createdUser = userRepository.save(newUser);

		return createdUser.getId();
	}
	
	@Override
	public UserDto getUserByUserName(String userName) {
		UserEntity user = userRepository.findByUsername(userName);
		return mapper.map(user, UserDto.class);
	}

}
