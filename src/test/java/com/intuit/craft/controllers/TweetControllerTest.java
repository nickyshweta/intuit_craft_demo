/**
 * 
 */
package com.intuit.craft.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.InternalServerErrorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import com.intuit.craft.beans.Tweet;
import com.intuit.craft.services.TweetServiceImpl;

/**
 * @author nicky
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = TweetController.class, secure = false)
public class TweetControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TweetServiceImpl tweetService;
	
	@InjectMocks
	private TweetController tweetController;
	
	@Test
	public void testPostTweet() throws Exception{
		Tweet mockTweet = new Tweet(1L,"Yes its Unit test tweet",1L,new Timestamp(new Date().getTime()));
		String exampleTweetJson="{\"content\":\"Yes this is Unit test\",\"userId\":\"10\"}";
		// Tweetservice.postTweet to respond back with mockTweet
				Mockito.when(
						tweetService.postTweet(Mockito.any(Tweet.class)))
				.thenReturn(mockTweet);
				
				// Send Tweet as body  
				RequestBuilder requestBuilder = MockMvcRequestBuilders
						.post("/twitter/v1/tweet")
						.accept(MediaType.APPLICATION_JSON).content(exampleTweetJson)
						.contentType(MediaType.APPLICATION_JSON);
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();

				MockHttpServletResponse response = result.getResponse();

				assertEquals(HttpStatus.CREATED.value(), response.getStatus());


	}
	
	@Test
	public void testGetLatestTweets() throws Exception{
		
		ArrayList<Tweet> mockLatestTweets = new ArrayList<Tweet>();
		mockLatestTweets.add(new Tweet(1L,"Yes its Unit test tweet",1L,new Timestamp(new Date().getTime())));
		mockLatestTweets.add(new Tweet(2L,"Yes its Unit test tweet",2L,new Timestamp(new Date().getTime())));
		
		Mockito.when( tweetService.getLatestTweets()).thenReturn(mockLatestTweets);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/twitter/v1/feed")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
}
	
	@Test(expected=InternalServerErrorException.class)
	public void testPostTweetException() throws Exception{

		Mockito.when(tweetService.postTweet(Mockito.any(Tweet.class))).thenThrow(InternalServerErrorException.class);
		
		BindingResult bindingResult = mock(BindingResult.class);
		tweetController.postTweet(new Tweet(), bindingResult);
		
	}
	
	@Test
	public void testGetLatestTweets_Negative() throws Exception{
		
		ArrayList<Tweet> mockLatestTweets = new ArrayList<Tweet>();
		
		Mockito.when( tweetService.getLatestTweets()).thenReturn(mockLatestTweets);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/twitter/v1/feed")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

	}

	

}
