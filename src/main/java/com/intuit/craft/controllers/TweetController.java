/**
 * 
 */
package com.intuit.craft.controllers;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.beans.Tweet;
import com.intuit.craft.services.TweetService;
import com.intuit.craft.services.TweetServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

/**
 * @author nicky
 *
 */
@RestController
@RequestMapping(value = "/twitter/v1")
@Api(value="Tweet Controller",description="Rest Endpoints for posting the tweet and getting the Feed")
public class TweetController {

	private static final Logger LOGGER = getLogger(TweetController.class);
	@Autowired
	private TweetServiceImpl tweetService;
	
	 @RequestMapping(value = "/tweet",
	            method = RequestMethod.POST,
	            consumes = {"application/json"},
	            produces = {"application/json"})
	   @ApiOperation(value="Post a tweet", response=Tweet.class,notes="Pass  valid userId with content to post")
	public ResponseEntity<Tweet> postTweet(@Valid @RequestBody Tweet tweet, BindingResult bindingResult)
		throws BadRequestException, InternalServerErrorException{
		
		 LOGGER.debug("inside Tweet");
		if(bindingResult.hasErrors()){
			throw new BadRequestException("User Id or the message is/are missing in the request");
		}
		
		try{
			Tweet postedTweet = tweetService.postTweet(tweet);
			return new ResponseEntity<Tweet>(postedTweet, HttpStatus.CREATED);
		}catch (Exception ex) {
			throw new InternalServerErrorException(ex.getMessage());
		}
	}

	 @RequestMapping(value = "/feed",
	            method = RequestMethod.GET,
	            produces = {"application/json"})
	 @ApiOperation(value="Get feed for user and its followees", response=ArrayList.class,notes="Returns top 100 messages in chronological order for user and its followers")
    public ResponseEntity<ArrayList<Tweet>> getLatestTweets()
    	throws InternalServerErrorException{
    	
    	try{
	        ArrayList<Tweet> listOfLatestTweets = new ArrayList<>();
	        listOfLatestTweets = tweetService.getLatestTweets();
	
	        if(listOfLatestTweets!=null && !listOfLatestTweets.isEmpty()){
	            return new ResponseEntity<ArrayList<Tweet>>(listOfLatestTweets, HttpStatus.OK);
	        }
	        else{
	            return new ResponseEntity<ArrayList<Tweet>>(HttpStatus.NO_CONTENT);
	        }
    	}catch (Exception ex) {
			// TODO: handle exception
    		throw new InternalServerErrorException(ex.getMessage());
		}
    }
}
