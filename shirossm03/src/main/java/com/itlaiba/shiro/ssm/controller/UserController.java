package com.itlaiba.shiro.ssm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itlaiba.shiro.ssm.pojo.TUser;
import com.itlaiba.shiro.ssm.service.UserImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserImpl impl;
	
	@RequestMapping("/show/{name}/{password}")
	public String show(@PathVariable String name,@PathVariable String password){
//		TUser user = impl.select(name);
//		创建登陆用户
		Subject subject = SecurityUtils.getSubject();
//		保存登陆用户的账号密码（只是表单中输入的）
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		try {
//			这里去myrealm中验证是否正确
			subject.login(token);
		} catch (Exception e) {
			System.out.println("异常了");
			return "error";
		}
		return "s";
	}
}
