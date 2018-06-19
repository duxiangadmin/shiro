package com.itlaiba.shiro.realm;

import java.sql.Connection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.itlaiba.shiro.dao.UserDao;
import com.itlaiba.shiro.dbutil.Dbutils;
import com.itlaiba.shiro.entity.User;

public class MyRealm extends AuthorizingRealm{

	private UserDao userDao =new UserDao();
	private Dbutils dbUtil = new Dbutils();
	
	/*
	 * 为当前用户赋予角色及权限(登陆成功之后)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		Connection  con =null;
		try {
			con = dbUtil.getcon();
			auth.setRoles(userDao.getRoles(con,username));
			auth.setStringPermissions(userDao.getPer(con,username));

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return auth;
	}

	/*
	 * 验证登陆（账号密码）
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		获取登陆信息账号
		String username = (String) token.getPrincipal();
		Connection con =null;
		try {
			con = dbUtil.getcon();
//			根据账号查出账号信息
			User user = userDao.getUser(con, username);
			if(user!=null){
//				把查出来的账号密码给shiro，shiro去判断是否正确
				AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "xx");
				return info;
			}
		} catch (Exception e) {

		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
