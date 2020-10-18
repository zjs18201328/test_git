package javabean;

public class Account {
	String name=null;//用户名
	String password=null;//密码
	int money=0;//账户剩余资金
	
	public Account(String name,String password,int money){
		super();
		this.name=name;
		this.password=password;
		this.money=money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
