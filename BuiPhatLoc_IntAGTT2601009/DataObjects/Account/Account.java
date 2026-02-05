package Account;

import java.util.HashMap;
import java.util.Map;

public class Account {
	
	public static class AccountInfo {
		private String username;
		private String password;
		private String pid;
		
		public AccountInfo(String username, String password, String pid) {
			this.username = username;
			this.password = password;
			this.pid = pid;
		}
		
		public String getUsername() {return this.username;}
		public String getPassword() {return this.password;}
		public String getPID() {return this.pid;}
	}
	
	private static final Map<String, AccountInfo> accountMap = new HashMap<String, Account.AccountInfo>();
	
	static {
		accountMap.put("TC1", new AccountInfo(
				"Homelander123@guerillamail.com", 
				"Homelander@123456",
				""));
		
		accountMap.put("TC2", new AccountInfo(
				"", 
				"Loc@123456",
				""));
		
		accountMap.put("TC3", new AccountInfo(
				"phatlocbui@gmail.com", 
				"123456789",
				""));
		
		accountMap.put("TC4", new AccountInfo(
				"phatlocbui@gmail.com", 
				"123456789",
				""));
		
		accountMap.put("TC5", new AccountInfo(
				"SuperDuper@guerrillamail.info", 
				"123456789",
				""));
		
		accountMap.put("TC6", new AccountInfo(
				"phatlocbui@gmail.com", 
				"Loc@123456",
				""));
		
	}
	
	public static AccountInfo getAccountInfo (String tcID) {
		AccountInfo account = accountMap.get(tcID);
		return account;
	}
}
