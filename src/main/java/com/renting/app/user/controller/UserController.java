package com.renting.app.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renting.app.common.JwtTokenUtil;
import com.renting.app.user.dto.LoginDto;
import com.renting.app.user.dto.LoginResultDto;
import com.renting.app.user.dto.RegisterUserDto;
import com.renting.app.user.dto.UserDto;
import com.renting.app.user.service.JwtUserDetailsService;
import com.renting.app.user.service.UserService;

@RequestMapping("users")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@GetMapping
	public List<UserDto> getUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/register")
	public LoginResultDto registerUser(@RequestBody RegisterUserDto registerUserDto) throws Exception {
		userService.registerUser(registerUserDto);
		return this.getToken(registerUserDto.getUsername(), registerUserDto.getPassword());
	}

	@PostMapping("/login")
	public LoginResultDto login(@RequestBody LoginDto loginDto) throws Exception {
		return this.getToken(loginDto.getUsername(), loginDto.getPassword());
	}

	@GetMapping("/currentUser")
	public UserDto currentUser(Authentication auth) throws Exception {
		return userService.getUserByUserName(auth.getName());
	}

	private LoginResultDto getToken(String userName, String password) throws Exception {
		this.authenticate(userName, password);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		final String token = jwtTokenUtil.generateToken(userDetails);

		LoginResultDto loginResult = new LoginResultDto();
		loginResult.setToken(token);
		loginResult.setUsername(userName);
		return loginResult;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
