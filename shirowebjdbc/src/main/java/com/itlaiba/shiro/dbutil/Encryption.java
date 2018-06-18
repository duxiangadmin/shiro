package com.itlaiba.shiro.dbutil;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密类
 * @author 杜翔
 * @版本 V 1.0 
 * @date 2018年6月18日 上午10:52:14
 */
public class Encryption {

	/*
	 * md5加密，不可逆
	 */
	public static String encMd5(String str,String enc){
		return new Md5Hash(str, enc).toString();
	}
	/*
	 * base64加密
	 */
	public static String encBase64(String str){
		return Base64.encodeToString(str.getBytes());
	}
	
	/*
	 * base64解密
	 */
	public static String decBase64(String str){
		return Base64.decodeToString(str);
	}
	
	
	
	public static void main(String[] args) {
		String password = "123456";
		System.out.println("md5加密"+encMd5("admin","itlaiba"));
		System.out.println("base64加密"+encBase64("admin"));
		System.out.println("base64解密"+decBase64(encBase64("admin")));
	}
}
