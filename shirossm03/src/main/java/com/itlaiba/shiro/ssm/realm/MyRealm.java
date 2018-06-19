package com.itlaiba.shiro.ssm.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.itlaiba.shiro.ssm.pojo.TUser;
import com.itlaiba.shiro.ssm.service.UserImpl;

public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	UserImpl impl;
	
//	这里是验证角色及权限的，根据配置文件中的配置，如果访问的是需要权限及角色的就过来验证	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		这里从页面拿到账号
		String username = (String) principals.getPrimaryPrincipal();
//		根据账号去数据库查到匹配的角色或权限信息
		TUser user = impl.selectbyrolename(username);
		TUser user2 = impl.selectbyper(username);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		实例一个set放着角色信息
		Set<String> set = new HashSet<String>();
		set.add(user.getRolename());
		Set<String> set2 = new HashSet<String>();
		set2.add(user2.getPer());
		try {
//			给当前账号赋予数据库查出来的角色，根据配置文件确定走向
			authorizationInfo.setRoles(set);
//			给当前账号赋予数据库查出来的权限，根据配置文件确定走向
			authorizationInfo.setStringPermissions(set2);
		} catch (Exception e) {
			System.out.println("异常了");
		}
		return authorizationInfo;
	}

//登陆验证	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		从登陆令牌中拿到登陆的账号
		String username = (String) token.getPrincipal();
//		根据账号去数据库查询信息
		TUser user = impl.select(username);
//		如果查出来的不等于null，就去判断账号密码是否正确
		if(user!=null){
//			根据数据库查出来的账号密码与页面提交过来的是否匹配
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),"x");	
//			这里走完直接去登陆方法，如果对就往下执行，不对就异常
			return authenticationInfo;
		}
//		如果查不出来直接返回null
		return null;
	}

}
