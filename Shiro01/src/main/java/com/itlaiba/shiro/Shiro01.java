package com.itlaiba.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Shiro01 {
	public static void main(String[] args) {
//		加载配置文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//		获取实例对象
		SecurityManager securityManager = factory.getInstance();
//		绑定实例到utils中
		SecurityUtils.setSecurityManager(securityManager);
//		得到当前用户
		Subject currentUser = SecurityUtils.getSubject();
//		创建token临牌 用户名密码
		UsernamePasswordToken token = new UsernamePasswordToken("lisi","123");
		try {
//			身份认证
			currentUser.login(token);
			System.out.println("登陆成功");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("失败");
		}
//		退出
		currentUser.logout();
	}
}
