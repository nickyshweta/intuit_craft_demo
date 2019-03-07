package com.intuit.craft.controllers;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.BadRequestException;
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
import static org.mockito.Mockito.mock;

import com.intuit.craft.beans.Follower;
import com.intuit.craft.services.FollowerServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FollowerController.class, secure = false)
public class FollowerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FollowerServiceImpl followService;
	
	@InjectMocks
	FollowerController followerController;
	
	@Test
	public void testFollowuser() throws Exception{
		Follower mockFollower= new Follower(1L, 2L, 3L);
		String exampleFollowerJson="{\"userId\":\"2\",\"followerId\":\"3\"}";

		Mockito.when(followService.followUser(Mockito.any(Follower.class))).thenReturn(mockFollower);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/twitter/v1/follow")
				.accept(MediaType.APPLICATION_JSON).content(exampleFollowerJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
				
	}
	
	
	@Test(expected=InternalServerErrorException.class)
	public void testFollowuserException() throws Exception{

		Mockito.when(followService.followUser(Mockito.any(Follower.class))).thenThrow(InternalServerErrorException.class);
		
		BindingResult bindingResult = mock(BindingResult.class);
		followerController.followUser(new Follower(), bindingResult);
		
	}
	
	@Test
	public void testUnFollowuser() throws Exception{
		String exampleFollowerJson="{\"userId\":\"2\",\"followerId\":\"3\"}";
		
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/twitter/v1/unfollow")
				.accept(MediaType.APPLICATION_JSON).content(exampleFollowerJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
				
	}
	
	@Test(expected=InternalServerErrorException.class)
	public void testUnFollowuserException() throws Exception{

		Mockito.when(followService.followUser(Mockito.any(Follower.class))).thenThrow(InternalServerErrorException.class);
		
		BindingResult bindingResult = mock(BindingResult.class);
		followerController.unFollowUser(new Follower(), bindingResult);
		
	}

}
