/**
 * 
 */
package com.intuit.craft.services;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.intuit.craft.beans.Tweet;
import com.intuit.craft.controllers.UserController;
import com.intuit.craft.jpa.TweetRepository;
import com.intuit.craft.jpa.UserRepository;

/**
 * @author nicky
 *
 */
@Service
public class TweetServiceImpl implements TweetService {

	private static final Logger LOGGER = getLogger(TweetServiceImpl.class);
	
	private HashMap<String,Long> LoggedInUserId=new HashMap<String,Long>();
	@Autowired
	TweetRepository tweetRepository;
	
	@Autowired
	FollowerService followerService;
	
	@Autowired
	UserRepository userRepository;
	
	
	private Long getUserId(){
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		final Authentication authentication = securityContext.getAuthentication();
		if(authentication!=null){
			LOGGER.info("Logged In user: "+authentication.getName());
			return getUserIdHelper(authentication.getName());
		}else
			return (long) 0;
	}
	
	@Override
	public Tweet postTweet(Tweet tweet) {
		// TODO Auto-generated method stub
		return tweetRepository.save(tweet);
	}

	@SuppressWarnings("null")
	@Override
	public ArrayList<Tweet> getLatestTweets() {
		// TODO Auto-generated method stub
				Long userId = getUserId();
				LOGGER.info("User Id"+userId);
				ArrayList<Tweet> latestTweets = new ArrayList<>();
				ArrayList<Long> listOfFollowing = new ArrayList<>();
				listOfFollowing=followerService.getListOfFollowingUsers(userId);
				if(listOfFollowing==null){
					listOfFollowing.add(userId);
				}
				
				LOGGER.info("list of following userids"+listOfFollowing.toString());
				latestTweets = tweetRepository.findByUserIdInOrderByPublishDateDesc(listOfFollowing);
				return latestTweets;
	}
	
	private Long getUserIdHelper(String name){
		HashMap<String,Long> userIds= new HashMap<String,Long>();
		userIds.put("admin", 1L);
		userIds.put("test", 2L);
		
		return !name.isEmpty()?userIds.get(name):(long)1;
	}

}
