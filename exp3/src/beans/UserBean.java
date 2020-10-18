package beans;

public class UserBean {
	private int id;
	private String account;
	private String password;
	private int type;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
/*	public boolean login()//新增，用于验证账号和密码
	{
		UserDBBean userDB=new UserDBBean();
		return userDB.valid(account, password);
	}
*/
}
