package com.itlaiba.shiro.ssm.pojo;

public class TUser {
    private Integer uid;

    private String username;

    private String password;

    private Integer roleid;
//    角色
    private String rolename;
//    权限
    private String per;
    public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}