package com.itlaiba.shiro.ssm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itlaiba.shiro.ssm.pojo.TUser;
import com.itlaiba.shiro.ssm.service.TuserService;

@Controller
@RequestMapping("/user")
public class UserController{
	
	@Autowired
	TuserService uservice;
	
	@RequestMapping("/login")
	public String show(TUser user){
		Subject subject = SecurityUtils.getSubject();
//		拿到页面传过来的账号密码，做一个令牌
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
//			去自定义realm中做撇匹配
//			匹配成功就往下执行
//			撇屁失败就异常
			subject.login(token);
			return "index";
		} catch (Exception e) {
			return "error";
		}		
	}
}
