package com.itlaiba.shiro.utils;

import java.util.Arrays;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.itlaiba.shiro.common.ShiroUtil;

public class RolesUtil {

	@Test
	public void testPermit() {
		/*
		 * 测试权限
		 */
		Subject subject = ShiroUtil.login("classpath:shiro.ini", "admin", "admin");		
		System.out.println(subject.isPermitted("user:select"));
		System.out.println(subject.isPermitted("user:add"));
		System.out.println(subject.isPermitted("delete"));
		
		subject = ShiroUtil.login("classpath:shiro.ini", "lisi", "123");		
		System.out.println(subject.isPermitted("user:select"));
		System.out.println(subject.isPermitted("user:delete"));
		
		
		subject = ShiroUtil.login("classpath:shiro.ini", "lisi", "123");
		boolean[] bs = subject.isPermitted("user:select","user:add");
		
	}
	
	
	@Test
	public void testcheckPermit() {
		/*
		 * 测试权限
		 */
//		Subject subject = ShiroUtil.login("classpath:shiro.ini", "admin", "admin");
		Subject subject = ShiroUtil.login("classpath:shiro.ini", "lisi", "123");
		subject.checkPermission("user:add");
		
	}
}
