package com.redis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.demo.request.UserRegisterRequest;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	public void saveUserInfo(UserRegisterRequest userRegisterRequest) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		redisTemplate.opsForHash().put("userInfo", userRegisterRequest.getjSessionId(), objectMapper.writeValueAsString(userRegisterRequest));
	}
	
	public void incrementUrlHitCounter(){
		redisTemplate.opsForValue().increment("urlCounter");
	}
}
