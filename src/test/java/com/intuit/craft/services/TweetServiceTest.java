package com.intuit.craft.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import com.intuit.craft.beans.Tweet;
import com.intuit.craft.jpa.FollowerRepository;
import com.intuit.craft.jpa.TweetRepository;

@RunWith(MockitoJUnitRunner.class)
public class TweetServiceTest {

	@Mock
	private FollowerServiceImpl followerService;
	
	@InjectMocks
	private TweetServiceImpl tweetService;
	
	@Mock
	private TweetRepository mockTweetRepository;
	
	@Mock
	private FollowerRepository mockFollowerRepository;
	
	//SecurityContext securityContext=Mockito.mock(SecurityContext.class);
	//Authentication authentication =Mockito
	//		.mock(Authentication.class);
	
	private Tweet tweet, tweet1;
	
	@Before
	public void init(){
		tweet = new Tweet();
		tweet.setId(1L);
		tweet.setUserId(2L);
		tweet.setContent("Test Content");
		tweet.setPublishDateonCreate();
		
		tweet1 = new Tweet();
		tweet1.setId(1L);
		tweet1.setUserId(3L);
		tweet1.setContent("Test Content");
		tweet1.setPublishDateonCreate();
		
	}
	
	@Test
	public void testPostTweet() {
		Mockito.when(mockTweetRepository.save(tweet)).thenReturn(tweet);
		
		Tweet tweeted = tweetService.postTweet(tweet);
		
		Assert.assertNotNull(tweeted);
	//	Mockito.verifyNoMoreInteractions(mockTweetRepository);
		
	}
	
	@Test
	public void testGetLatestFeed() throws Exception{
		Long userId=1L;
		ArrayList<Long> mockListOfFollowing =new ArrayList<Long>();
		mockListOfFollowing.add(2L);
		mockListOfFollowing.add(3L);
		
		Mockito.when(followerService.getListOfFollowingUsers(userId))
		.thenReturn(mockListOfFollowing);
		
		ArrayList<Tweet> mockLatestTweets =new ArrayList<>(Arrays.asList(tweet,tweet1));
		Mockito.when(tweetService.getLatestTweets()).thenReturn(mockLatestTweets);
		
		
		ArrayList<Tweet> resultLatestTweets = tweetService.getLatestTweets();
		
		Assert.assertThat(resultLatestTweets, Matchers.hasSize(2));
		Mockito.verify(mockTweetRepository, Mockito.times(1)).findByUserIdInOrderByPublishDateDesc(mockListOfFollowing);
		Mockito.verifyNoMoreInteractions(mockTweetRepository);
		
	}
}
