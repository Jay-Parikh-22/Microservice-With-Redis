package com.redis.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class StickySessionComp {

	@Autowired
	RedisTemplate<String, String> redisTemplate;
	
	public void setKey(){
		//redisTemplate.opsForValue().set("myVar", "9999");
		redisTemplate.opsForValue().increment("myVar");
	}
	
	@PostConstruct
	public void postConstruct(){
		setKey();
	}
}

