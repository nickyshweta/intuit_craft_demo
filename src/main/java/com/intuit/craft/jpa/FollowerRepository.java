package com.intuit.craft.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intuit.craft.beans.Follower;
@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long>{

	Follower findByUserIdAndFollowerId(Long userId, Long followerId);
	
	@Query(value = "SELECT f.follower_id FROM Follower f  WHERE f.user_id=:userId", nativeQuery = true)
	ArrayList<Long> findFollowerIdByUserId(@Param(value = "userId") Long userId);
}
