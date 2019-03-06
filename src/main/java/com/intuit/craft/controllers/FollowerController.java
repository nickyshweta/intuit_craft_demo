/**
 * 
 */
package com.intuit.craft.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.beans.Follower;
import com.intuit.craft.services.FollowerService;
import com.intuit.craft.services.FollowerServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
/**
 * @author nicky
 *
 */

@RestController
@RequestMapping(value = "/twitter/v1")
@Api(value="Follower Controller", description="Manage Follow and and Unfollow user")
public class FollowerController {
	
	private static final Logger LOGGER = getLogger(FollowerController.class);
	@Autowired
	private FollowerServiceImpl followerService;
		
	@RequestMapping(value = "/follow", 
			method = RequestMethod.PUT, 
			produces= "application/json")
	@ApiOperation(value="Follow user", response=Follower.class,notes="User can follow other user")
	public ResponseEntity<Follower> followUser(@Valid @RequestBody Follower follower, BindingResult bindingResult)
		throws BadRequestException, InternalServerErrorException{
		
		LOGGER.debug("Inside Follow");
		if(bindingResult.hasErrors())
			throw new BadRequestException("Follower or user details missing in the request");
		try{
			Follower updatefollower = followerService.followUser(follower);
			return new ResponseEntity<Follower>(updatefollower, HttpStatus.OK);
		}catch(Exception ex){
			throw new InternalServerErrorException(ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/unfollow", 
			method = RequestMethod.DELETE, 
			produces= "application/json")
	@ApiOperation(value="Unfollow user",notes="User can unfollow other user")
	public ResponseEntity<Void> unFollowUser(@Valid @RequestBody Follower follower, BindingResult bindingResult)
		throws BadRequestException, InternalServerErrorException{
		
		if(bindingResult.hasErrors())
			throw new BadRequestException("Follower or user details missing in the request");
		try{
			 followerService.unFollowUser(follower);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception ex){
			throw new InternalServerErrorException(ex.getMessage());
		}
	}

}
