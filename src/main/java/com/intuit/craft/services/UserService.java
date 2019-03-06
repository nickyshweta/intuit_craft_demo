package com.intuit.craft.services;

import com.intuit.craft.beans.User;

public interface UserService {

	 public User save(User user);
	 public User findByUserId(Long userId);
}
