package com.bridgelabz.fundoonotes.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.user.model.LoginDto;
import com.bridgelabz.fundoonotes.user.model.RegisterDto;
import com.bridgelabz.fundoonotes.user.model.ResetPasswordDto;
import com.bridgelabz.fundoonotes.user.services.IUserService;

/**
 * Handles requests for the application.
 */
@RestController
public class UserController {

	//private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@Valid @RequestBody LoginDto user) {

		boolean userlogin = userService.login(user);

		if (userlogin) {

			return new ResponseEntity<String>("User Logged In Successfully", HttpStatus.OK);

		}

		return new ResponseEntity<String>("User LogIn Failed", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@Valid @RequestBody RegisterDto user, HttpServletRequest request) {

		String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
				+ "/verify/";

		long status = userService.register(user, url);

		if (status > 0) {

			return new ResponseEntity<String>("User registration successfully", HttpStatus.CREATED);

		}
		return new ResponseEntity<String>("User registration unsuccessfully", HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/verify/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<String> verify(@PathVariable String token) {
		
		System.out.println(token);

		userService.verify(token);

		return new ResponseEntity<String>("isVerified", HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ResponseEntity<String> forgotPassword(@Valid @RequestBody ResetPasswordDto userEmail, HttpServletRequest request) {
		
		String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
				+ "/resetpassword/";
		
		System.out.println("cont"+userEmail.getEmailId());
		
		userService.forgotPassword(userEmail, url);
		
		return new ResponseEntity<String>("Forgot Password", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@RequestMapping(value = "/resetpassword/{token:.+}", method = RequestMethod.PUT)
	public ResponseEntity<String> resetPassword(@Valid @PathVariable String token, @RequestBody ResetPasswordDto userPassword) {
		
		boolean status = userService.resetPassword(userPassword,token);
		
		if(status) {
			
			return new ResponseEntity<String>("Password Reset", HttpStatus.OK);
			
		}
		
		return new ResponseEntity<String>("Could not Reset! Try again!", HttpStatus.BAD_REQUEST);
	}
}
