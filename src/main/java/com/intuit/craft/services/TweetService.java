/**
 * 
 */
package com.intuit.craft.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.intuit.craft.beans.Tweet;

/**
 * @author nicky
 *Service Interface for creating and fetching the tweets
 */

@Service
public interface TweetService {
	
	public Tweet postTweet(Tweet tweet);
	public ArrayList<Tweet> getLatestTweets();

}
