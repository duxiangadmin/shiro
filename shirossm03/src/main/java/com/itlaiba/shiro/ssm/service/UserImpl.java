package com.itlaiba.shiro.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itlaiba.shiro.ssm.mapper.TUserMapper;
import com.itlaiba.shiro.ssm.pojo.TUser;

@Service
public class UserImpl {

	@Autowired
	TUserMapper mapper;
	
	public TUser select(String name){
		return mapper.selectbyname(name);
	}
	
	public TUser selectbyrolename(String name){
		return mapper.selectbyrolename(name);
	}
	public TUser selectbyper(String name){
		return mapper.selectbyper(name);
	}
}
