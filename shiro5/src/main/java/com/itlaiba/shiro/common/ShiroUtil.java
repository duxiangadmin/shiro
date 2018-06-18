package com.itlaiba.shiro.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroUtil {
	public static Subject login(String config,String username,String password){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(config);
		SecurityManager instance = factory.getInstance();
		SecurityUtils.setSecurityManager(instance);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			System.out.println("登陆成功");
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("登陆失败");
		}
		return subject;
	}
}
