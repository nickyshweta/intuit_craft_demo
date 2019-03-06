/**
 * 
 */
package com.intuit.craft.services;

import java.util.ArrayList;

import com.intuit.craft.beans.Follower;

/**
 * @author nicky
 *
 */
public interface FollowerService {
	
	public Follower followUser(Follower follower);
	
	public void unFollowUser(Follower follower);
	
	public ArrayList<Long> getListOfFollowingUsers(Long userId);
}
