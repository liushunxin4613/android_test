package entity;

/**
 * 用户
 * @author macos
 *
 */
public class User {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 是否记住密码
	 */
	private boolean isRemember;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isRemember() {
		return isRemember;
	}
	public void setRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}
	public User(String username, String pwd) {
		this.username = username;
		this.pwd = pwd;
	}
	public User() {
	}
	
}
