package com.itlaiba.shiro.ssm.mapper;

import com.itlaiba.shiro.ssm.pojo.TUser;

public interface TUserMapper {
	
	public TUser selectbyname(String username);

}