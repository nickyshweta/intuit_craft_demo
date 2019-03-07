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

import com.intuit.craft.beans.Follower;
import com.intuit.craft.beans.Tweet;
import com.intuit.craft.jpa.FollowerRepository;

@RunWith(MockitoJUnitRunner.class)
public class FollowerServiceTest {
	
	@Mock
	private FollowerRepository mockFollowerRepository;
	
	@InjectMocks
	private FollowerServiceImpl followService;
	
	
	private Follower follower, follower1;
	
	@Before
	public void init(){
		follower = new Follower();
		follower.setId(1L);
		follower.setUserId(2L);
		follower.setFollowerId(3L);
		
		follower1 = new Follower();
		follower1.setId(11L);
		follower1.setUserId(22L);
		follower1.setFollowerId(33L);
		
	}
	@Test
	public void testFollowuser(){
		Mockito.when(mockFollowerRepository.
				findByUserIdAndFollowerId(follower.getUserId(), follower.getFollowerId()))
				.thenReturn(null);
		Mockito.when(mockFollowerRepository.save(follower)).thenReturn(follower);
		Follower followedUser = followService.followUser(follower);
		
		Assert.assertEquals(followedUser,follower);
		
	}
	
	/*@Test
	public void testUnfollowuser(){
		Mockito.when(mockFollowerRepository.
				findByUserIdAndFollowerId(follower.getUserId(), follower.getFollowerId()))
				.thenReturn(follower);
		FollowerServiceImpl mockFollowService = Mockito.mock(FollowerServiceImpl.class);
		Mockito.doNothing().when(mockFollowService).unFollowUser(follower);
		
		
	}*/
	
	@Test
	public void testGetListOfFollowingUser(){
		Long userId=1L;
		ArrayList<Long> mockListOfFollowing =new ArrayList<Long>();
		mockListOfFollowing.add(2L);
		mockListOfFollowing.add(3L);
		Mockito.when(mockFollowerRepository.findFollowerIdByUserId(userId)).thenReturn(mockListOfFollowing);
		
		ArrayList<Long> listOfFollowingUser = followService.getListOfFollowingUsers(userId);
		
		Assert.assertThat(listOfFollowingUser, Matchers.hasSize(2));
		Mockito.verify(mockFollowerRepository, Mockito.times(1)).findFollowerIdByUserId(userId);
		Mockito.verifyNoMoreInteractions(mockFollowerRepository);
		
	}
	

}
