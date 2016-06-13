package com.app.code.model;

import java.util.Date;

/**
 * 
 * @author: test
 * @date: 2016-06-13 15:19:53
 * create by codeFactory
 */
public class User {
	public Integer id;
	
	public String username;
	
	public String password;
	
	public String email;
	
	public Date birthday;
	
	/**
	 * 0：女 1：男
	 */
	public Integer sex;
	
	/**
	 * 员工状态：1—正常 2—离职
	 */
	public Integer status;
	
	public String loginIp;
	
	public Date loginTime;
	
	/**
	 * 上次登录ip
	 */
	public String lastLoginIp;
	
	/**
	 * 上次登录时间
	 */
	public Date lastLoginTime;
	
	public Date inputTime;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User [");
			sb.append("id=" + id + ",");
			sb.append("username=" + username + ",");
			sb.append("password=" + password + ",");
			sb.append("email=" + email + ",");
			sb.append("birthday=" + birthday + ",");
			sb.append("sex=" + sex + ",");
			sb.append("status=" + status + ",");
			sb.append("loginIp=" + loginIp + ",");
			sb.append("loginTime=" + loginTime + ",");
			sb.append("lastLoginIp=" + lastLoginIp + ",");
			sb.append("lastLoginTime=" + lastLoginTime + ",");
			sb.append("inputTime=" + inputTime + ",");
		sb.replace(sb.length()-1, sb.length(), " ]");
		return sb.toString();
	}
	
	
}
