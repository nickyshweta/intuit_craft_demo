package com.intuit.craft.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intuit.craft.beans.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{

	ArrayList<Tweet> findAll();
	@Query(value = "SELECT * FROM Tweet t WHERE t.user_id IN :userIds ORDER BY t.publish_date DESC LIMIT 100", nativeQuery = true)
	ArrayList<Tweet> findByUserIdInOrderByPublishDateDesc(@Param("userIds") List<Long> userIds);
}
