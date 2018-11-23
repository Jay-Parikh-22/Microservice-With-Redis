package com.redis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	public String getUserInfo(String jSessionId) throws JsonProcessingException{
		String userInfo = (String) redisTemplate.opsForHash().get("userInfo", jSessionId);
		return userInfo;
	}
	
	public void incrementUrlHitCounter(){
		redisTemplate.opsForValue().increment("urlCounter");
	}
}
