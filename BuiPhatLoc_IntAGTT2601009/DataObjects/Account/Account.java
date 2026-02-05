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
	
	private static String customUsername = "MegamanX12@guerrillamail.com";
	private static String customPassword = "MegamanX@123456";
	private static String customPID = "0987654321";
	
	static {
		accountMap.put("TC1", new AccountInfo(
				customUsername, 
				customPassword,
				customPID));
		
		accountMap.put("TC2", new AccountInfo(
				"", 
				customPassword,
				""));
		
		accountMap.put("TC3", new AccountInfo(
				customUsername, 
				"123456789",
				""));
		
		accountMap.put("TC4", new AccountInfo(
				customUsername, 
				"123456789",
				""));
		
		accountMap.put("TC5", new AccountInfo(
				"Tester@Tester.Tester", 
				"Tester@13579",
				""));
		
		accountMap.put("TC6", new AccountInfo(
				customUsername, 
				customPassword,
				""));
		
	}
	
	public static AccountInfo getAccountInfo (String tcID) {
		AccountInfo account = accountMap.get(tcID);
		return account;
	}
}
