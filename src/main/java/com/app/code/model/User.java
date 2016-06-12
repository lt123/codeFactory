package com.app.code.model;

import java.util.Date;

/**
 * 
 * @author: test
 * @date: 2016-06-12 17:34:46
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
	
	public String login_ip;
	
	public Date login_time;
	
	/**
	 * 上次登录ip
	 */
	public String last_login_ip;
	
	/**
	 * 上次登录时间
	 */
	public Date last_login_time;
	
	public Date input_time;
	
	
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
	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public Date getInput_time() {
		return input_time;
	}

	public void setInput_time(Date input_time) {
		this.input_time = input_time;
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
			sb.append("login_ip=" + login_ip + ",");
			sb.append("login_time=" + login_time + ",");
			sb.append("last_login_ip=" + last_login_ip + ",");
			sb.append("last_login_time=" + last_login_time + ",");
			sb.append("input_time=" + input_time + ",");
		sb.replace(sb.length()-1, sb.length(), " ]");
		return sb.toString();
	}
	
	
}
