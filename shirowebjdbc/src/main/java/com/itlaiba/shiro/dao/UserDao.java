package com.itlaiba.shiro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.itlaiba.shiro.entity.User;

public class UserDao {
	public User getUser(Connection con ,String username) throws Exception{
		User user= null;
		String sql ="select * from t_user where username=?";
		PreparedStatement pStatement = con.prepareStatement(sql);
		pStatement.setString(1, username);
		ResultSet rs = pStatement.executeQuery();
		if(rs.next()){
			user= new User();
			user.setUserId(rs.getInt("uid"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));			
		}
		return user;
	}

	public Set<String> getRoles(Connection con, String username) throws Exception {
		Set<String> set = new HashSet<String>();
		String sql="select * from t_user u,t_role r where u.roleid=r.roleid and username=?";
		PreparedStatement pStatement =con.prepareStatement(sql);
		pStatement.setString(1, username);
		ResultSet rs = pStatement.executeQuery();
		while(rs.next()){
			set.add(rs.getString("rolename"));
		}
		return set;
	}

	public Set<String> getPer(Connection con, String username)throws Exception {
		Set<String> pers = new HashSet<String>();
		String sql="select * from t_user u,t_role r,t_per p where u.roleid = r.roleid and r.roleid=p.roleid and username=?";
		PreparedStatement pStatement =con.prepareStatement(sql);
		pStatement.setString(1, username);
		ResultSet rs = pStatement.executeQuery();
		while(rs.next()){
			pers.add(rs.getString("permsname"));
		}
		return pers;
	}
}
