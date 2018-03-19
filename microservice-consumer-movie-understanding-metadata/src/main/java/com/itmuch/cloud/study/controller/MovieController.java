package com.itmuch.cloud.study.controller;

import java.util.List;

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
	@Autowired
	private DiscoveryClient discoveryClient;
	@Value("${user.userServiceUrl.protocol}")
	private String protocol;
	@Value("${user.userServiceUrl.host}")
	private String host;
	@Value("${user.userServiceUrl.port}")
	private String port;
	
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		return this.restTemplate.getForObject(protocol + "://" + host + ":" + port + "/" + id, User.class);
	}
	
	@GetMapping("/user-instance/{serviceId}")
	public List<ServiceInstance> showInfo(@PathVariable String serviceId) {
		return this.discoveryClient.getInstances(serviceId);
	}
}
