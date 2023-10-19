package model;

public class User {
	private String login;
	private String password;
	
	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + "]";
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	
}
