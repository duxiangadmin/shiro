package com.itlaiba.shiro.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.itlaiba.shiro.common.ShiroUtil;

public class RolesUtil {

	@Test
	public void testhashRole() {
//		三个方法，返回值都是boolean
		/*
		 * 第一个测试一个角色
		 * 2.测试多个
		 * 3.测试多个是否都有
		 */
		Subject subject = ShiroUtil.login("classpath:shiro.ini", "admin", "admin");
		System.out.println(subject.hasRole("roles1")?"admin有roles1角色":"admin没有roles1");	
		System.out.println(subject.hasRole("roles2")?"admin有roles2角色":"admin没有roles2");	
		subject = ShiroUtil.login("classpath:shiro.ini", "lisi", "123");
		System.out.println(subject.hasRole("roles1")?"lisi有roles1角色":"lisi没有roles1");	
		System.out.println(subject.hasRole("roles2")?"lisi有roles2角色":"lisi没有roles2");	
		
		System.out.println("----------------------------");
		
		subject = ShiroUtil.login("classpath:shiro.ini", "lisi", "123");
		boolean[] bs = subject.hasRoles(Arrays.asList("roles1","roles2"));
		System.out.println(bs[0]?"lisi有roles1角色":"lisi没有roles1");
		System.out.println(bs[1]?"lisi有roles1角色":"lisi没有roles1");
		
		subject = ShiroUtil.login("classpath:shiro.ini", "lisi", "123");
		System.out.println(subject.hasAllRoles(Arrays.asList("roles1","roles2"))?"全有":"不全有");
	}
	
	
	@Test
	public void testckeckRole() {
//		三个方法，没有就异常
		Subject subject	 = ShiroUtil.login("classpath:shiro.ini", "admin", "admin");
		subject.checkRole("roles1");
		subject.checkRoles("roles1","roles2");
		subject.checkRoles("roles1","roles23");//报错，因为没有roles23这个角色
	}

}
