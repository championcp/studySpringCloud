package com.itmuch.cloud.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
	@Autowired
	private RestTemplate restTemplate;
	@Value("${user.userServiceId}")
	private String userServiceId;
	@Autowired
	DiscoveryClient discoveryClient;
	
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		ServiceInstance si = discoveryClient.getInstances(userServiceId).get(0);
		return this.restTemplate.getForObject(si.getUri() + "/" + id, User.class);
	}
}
