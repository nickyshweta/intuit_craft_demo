package com.intuit.craft.beans;

import org.jetbrains.annotations.NotNull;
/**
 * @author nicky
 * Entity class to store User data
 * WebToken bean class is used to generate username-password based authorization tokens
 */

public class WebToken {
	@NotNull
	public String userName;
	@NotNull
	public String password;
	
	public WebToken(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "WebToken [userName=" + userName + ", password=" + password + "]";
	}
	
}
