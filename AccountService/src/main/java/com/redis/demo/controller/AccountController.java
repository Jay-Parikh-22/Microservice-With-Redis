package com.redis.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.demo.request.UserRegisterRequest;
import com.redis.demo.service.RedisService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping(path="/getInfo", method=RequestMethod.GET)
	public UserRegisterRequest registerUser(@RequestParam("jSessionId") String jSessionId) throws IOException{
		//Going to increment url hit counter
		redisService.incrementUrlHitCounter();
		
		ObjectMapper mapper = new ObjectMapper();
		UserRegisterRequest userInfo = mapper.readValue(redisService.getUserInfo(jSessionId), UserRegisterRequest.class);
		return userInfo;
	}
}
