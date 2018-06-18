package com.itlaiba.shiro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.itlaiba.shiro.dbutil.Encryption;

public class Login extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login doget");
		resp.sendRedirect("login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login dopost");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Subject subject = SecurityUtils.getSubject();
//		这里是吧前台的数据给shiro，shiro去数据库匹对，此处的账号密码对应数据库中的，所以这里应该传入的是加密的密码
		UsernamePasswordToken token = new UsernamePasswordToken(username, Encryption.encMd5(password,"itlaiba"));
		try {
			subject.login(token);
			resp.sendRedirect("success.jsp");
		} catch (Exception e) {
			req.setAttribute("msg", "失败");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}
