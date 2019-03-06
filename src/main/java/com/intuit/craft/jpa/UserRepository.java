package com.intuit.craft.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intuit.craft.beans.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByName(String userId);

}