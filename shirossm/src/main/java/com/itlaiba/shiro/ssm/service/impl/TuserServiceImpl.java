package com.itlaiba.shiro.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itlaiba.shiro.ssm.mapper.TUserMapper;
import com.itlaiba.shiro.ssm.pojo.TUser;
import com.itlaiba.shiro.ssm.service.TuserService;
@Service
@Transactional
public class TuserServiceImpl implements TuserService{

	@Autowired
	private TUserMapper mapper;
	
	public TUser selectbyname(String username) {
		// TODO Auto-generated method stub
		return mapper.selectbyname(username);
	}

}
