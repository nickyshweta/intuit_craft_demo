package com.intuit.craft.services;

import org.springframework.stereotype.Service;
/**
 * @author nicky
 * Interface for services related to generation of authorization token
 */
@Service
public interface AuthTokenGenerator {
	public String generateToken();
}
