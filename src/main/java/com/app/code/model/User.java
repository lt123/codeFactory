package com.app.code.model;

public class User {
	private String username = "xxx";
	private String[] passwords = new String[]{"a", "b", "c"};
	private String[] usernames = new String[]{"1", "2", "3"};
	private boolean flag;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String[] getPasswords() {
		return passwords;
	}
	public void setPasswords(String[] passwords) {
		this.passwords = passwords;
	}
	public String[] getUsernames() {
		return usernames;
	}
	public void setUsernames(String[] usernames) {
		this.usernames = usernames;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
