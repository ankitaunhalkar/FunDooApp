package com.bridgelabz.fundoonotes.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDaoImpl implements ITokenDao{

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public void setToken(String key, String uname) {
		
		System.out.println("key"+key);
		
		System.out.println("name"+uname);
		
		redisTemplate.opsForValue().set(key, uname);
		
	}

	@Override
	public String getToken(String key) {
		
		System.out.println(key);
		
		return redisTemplate.opsForValue().get(key);
		
	}

	@Override
	public void deleteToken(String key) {
		
		redisTemplate.delete(key);
		
	}

}