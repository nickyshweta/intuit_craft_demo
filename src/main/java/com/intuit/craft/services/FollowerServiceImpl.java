/**
 * 
 */
package com.intuit.craft.services;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.intuit.craft.beans.Follower;
import com.intuit.craft.jpa.FollowerRepository;

/**
 * @author nicky
 *
 */

@Service
public class FollowerServiceImpl implements FollowerService {

	private static final Logger LOGGER = getLogger(FollowerServiceImpl.class);
	
	@Autowired
	FollowerRepository followerRepository;
	@Override
	public Follower followUser(Follower follower) {
		// TODO Auto-generated method stub
		Follower existing = followerRepository.findByUserIdAndFollowerId(follower.getUserId(), follower.getFollowerId());
		if(existing==null){
			return followerRepository.save(follower);
		}
		else{
			return null;
		}
		
	}

	@Override
	public void unFollowUser(Follower follower) {
		// TODO Auto-generated method stub
		Follower existing = followerRepository.findByUserIdAndFollowerId(follower.getUserId(),follower.getFollowerId());
		if(existing!=null){
			 followerRepository.delete(existing);
		}
		
	}

	@Override
	public ArrayList<Long> getListOfFollowingUsers(Long userId) {
		// TODO Auto-generated method stub
		LOGGER.info("userid" +userId);
		ArrayList<Long> followingList =  followerRepository.findFollowerIdByUserId(userId);
		
		
	//	ArrayList<Long> listOfFollowing =(ArrayList<Long>) StreamSupport.stream(followingList.spliterator(),false).
							//				map(Follower -> Follower.getFollowerId()).collect(Collectors.toList());
		
		LOGGER.info("listOfFollowing"+followingList.toString());
		return followingList;
	}

	
}
