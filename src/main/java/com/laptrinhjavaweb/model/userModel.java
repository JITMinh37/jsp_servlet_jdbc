package com.laptrinhjavaweb.model;

public class userModel extends abstractModel<userModel>{
	private String userName;
	private String passWord;
	private String fullName;
	private int status;
	private roleModel role;
	public roleModel getRole() {
		return role;
	}
	public void setRole(roleModel role) {
		this.role = role;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	private long roleid;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
