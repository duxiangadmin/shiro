package com.itlaiba.shiro.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbutils {
	public static Connection getcon () throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql:///shiro","root","123456");
		return con;
	}
	
	public static void closeCon (Connection con) throws Exception{
		con.close();
	}
	
	public static void main(String[] args) throws Exception {
		getcon();
	}
}
