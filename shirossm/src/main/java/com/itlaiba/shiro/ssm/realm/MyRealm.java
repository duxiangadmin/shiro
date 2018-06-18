package com.itlaiba.shiro.ssm.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.itlaiba.shiro.ssm.pojo.TUser;
import com.itlaiba.shiro.ssm.service.TuserService;

public class MyRealm extends AuthorizingRealm{
	
	@Autowired 
	TuserService service;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		根据令牌获得账号信息
		String username = (String) token.getPrincipal();
//		根据账号去数据库库中查到所有的信息
		TUser user = service.selectbyname(username);
//		这里拿到数据库中的账号信息，会自动和前台传过来的信息匹配
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),"xx");
//		完了会回到controller中的令牌中
		return simpleAuthenticationInfo;
	}

}
