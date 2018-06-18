package com.itlaiba.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import com.itlaiba.shiro.pojo.User;

public class TestShiro {
	public static void main(String[] args) {
//		用户
		User u1 = new User("admin", "123");
		User u2 = new User("li4", "123");
		User u3 = new User("zxc", "admin");
//		角色
		String roles = "admin";
		String product = "product";
//		权限
		String permitProduct ="addProduct";
		String permitOrder="addOrder";
		
//		加载配置文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//		获取实例对象
		SecurityManager securityManager = factory.getInstance();
//		绑定实例到utils中
		SecurityUtils.setSecurityManager(securityManager);
//		得到当前用户
		Subject currentUser = SecurityUtils.getSubject();
//		创建token临牌 用户名密码
		UsernamePasswordToken token = new UsernamePasswordToken(u1.getName(),u1.getPassword());
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
