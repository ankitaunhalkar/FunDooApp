package com.bridgelabz.fundoonotes.user.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoonotes.user.model.LoginDto;
import com.bridgelabz.fundoonotes.user.model.RegisterDto;
import com.bridgelabz.fundoonotes.user.services.IUserService;

/**
 * Handles requests for the application.
 */
@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@Valid @RequestBody LoginDto user) {
		
		boolean userlogin = userService.login(user);
		
		if (userlogin ) {
			
			return new ResponseEntity<String>("User Logged In Successfully", HttpStatus.OK);
			
		}
		
		return new ResponseEntity<String>("User LogIn Failed", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@Valid @RequestBody RegisterDto user) {

		
		logger.info("Creating User: " + user.getName());
		
		int status = userService.register(user);

		if (status > 0) {
			
			return new ResponseEntity<String>("User registration successfully", HttpStatus.CREATED);
			
		}
		return new ResponseEntity<String>("User registration unsuccessfully", HttpStatus.CONFLICT);
	}
}