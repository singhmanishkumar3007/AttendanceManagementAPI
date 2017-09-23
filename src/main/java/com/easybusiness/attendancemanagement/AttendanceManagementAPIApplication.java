package com.easybusiness.attendancemanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.easybusiness.attendancemanagement", "com.easybusiness.attendancepersistence"})
@EnableAutoConfiguration
public class AttendanceManagementAPIApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceManagementAPIApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AttendanceManagementAPIApplication.class, args);
		LOGGER.info("started AttendanceManagementAPIApplication");
	}

	@Override
	public void run(String... args) throws Exception {
	    LOGGER.info("in run method");
	    
	}

}
