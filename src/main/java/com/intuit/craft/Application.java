package com.intuit.craft;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application {
	
	private static final Class<Application> applicationClass = Application.class;
	 private static final Logger LOGGER = getLogger(applicationClass);
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.debug("Wohoo!! Application Started");
    }
    
   
}
