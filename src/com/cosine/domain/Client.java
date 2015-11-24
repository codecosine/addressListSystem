package com.cosine.domain;

public class Client {

/**
 * 用户实体类，包含属性：
 * 用户名
 * 密码
 * 权限 2最低，10最高
 */
	private String username;
	private String password;
	private int power;
	public Client(){
		
	}
	
	public Client(String username, String password, int power) {
		super();
		this.username = username;
		this.password = password;
		this.power = power;
	}

	public Client(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Client [username=" + username + ", password=" + password
				+ ", power=" + power + "]";
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
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


}
