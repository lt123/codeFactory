package com.app.code.model;

import java.util.Date;

/**
 * 用户表
 * @author: test
 * @date: 2016-06-12 17:34:06
 * create by codeFactory
 */
public class User2 {
	/**
	 * 用户主键
	 */
	public Integer id;
	
	/**
	 * 用户名
	 */
	public String name;
	
	/**
	 * 注册时间
	 */
	public Date create_date;
	
	public Date eee;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getEee() {
		return eee;
	}

	public void setEee(Date eee) {
		this.eee = eee;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User2 [");
			sb.append("id=" + id + ",");
			sb.append("name=" + name + ",");
			sb.append("create_date=" + create_date + ",");
			sb.append("eee=" + eee + ",");
		sb.replace(sb.length()-1, sb.length(), " ]");
		return sb.toString();
	}
	
	
}
