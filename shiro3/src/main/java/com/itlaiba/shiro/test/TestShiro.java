package com.itlaiba.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestShiro {
	public static void main(String[] args) {
//	读取配置文件
	Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//	获取实例
	SecurityManager instance = factory.getInstance();
//	绑定实例
	SecurityUtils.setSecurityManager(instance);
//	得到当前登录用户
	Subject subject = SecurityUtils.getSubject();
//	得到临牌
	UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
//	登陆测试
		try {
			subject.login(token);
			System.out.println("登陆成功");
		} catch (Exception e) {
			System.out.println("登陆失败");
		}
		subject.logout();
	}
}
