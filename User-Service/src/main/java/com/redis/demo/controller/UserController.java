package com.redis.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.redis.demo.service.RedisService;
import com.redis.demo.request.UserRegisterRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public String registerUser(@RequestBody UserRegisterRequest userRegisterRequest) throws JsonProcessingException{
		//Going to increment url hit counter
		redisService.incrementUrlHitCounter();
		
		userRegisterRequest.setjSessionId(UUID.randomUUID().toString());
		redisService.saveUserInfo(userRegisterRequest);
		return userRegisterRequest.getjSessionId();
	}
}
