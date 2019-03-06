/**
 * 
 */
package com.intuit.craft.controllers;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
/**
 * @author nicky
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.services.AuthTokenGenerator;
import com.intuit.craft.services.AuthTokenGeneratorImpl;

@RestController
//@RequestMapping(value = "/twitter/v1")
public class UserController {
	
	private static final Logger LOGGER = getLogger(UserController.class);
	@Autowired
	AuthTokenGenerator AuthTokenGenerator = new AuthTokenGeneratorImpl();

	@RequestMapping(value = "/user/login", 
			method = RequestMethod.GET,
			produces = "application/json")
	public ResponseEntity<String> generateAuthToken(){
		LOGGER.info("Calling Login method");
		return new ResponseEntity<String>(AuthTokenGenerator.generateToken(), HttpStatus.OK);
	}
}

