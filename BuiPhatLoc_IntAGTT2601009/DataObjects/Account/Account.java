package Account;

import java.util.HashMap;
import java.util.Map;

import Common.Utilities;

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
		public void setUsername(String username) {this.username = username;}
		public void setPassword(String password) {this.password = password;}
		public void setPID(String pid) {this.pid = pid;}
	}
	
	private static final Map<String, AccountInfo> accountMap = new HashMap<String, Account.AccountInfo>();
	
	private static String customUsernameLoginTest = Utilities.genUsernameString();
	private static final String customPassword = "BeTheOne@20170903_20180826";
	private static final String customPID = "0987654321";
	
	static {
		// LoginTest
		accountMap.put("TC1", new AccountInfo(
				customUsernameLoginTest, 
				customPassword,
				customPID));
		accountMap.put("TC2", new AccountInfo(
				"", 
				customPassword,
				""));
		accountMap.put("TC3", new AccountInfo(
				customUsernameLoginTest, 
				"123456789",
				""));
		accountMap.put("TC4", new AccountInfo(
				customUsernameLoginTest, 
				"123456789",
				""));
		accountMap.put("TC5", new AccountInfo(
				"Tester@Tester.Tester", 
				"Tester@13579",
				""));
		accountMap.put("TC6", new AccountInfo(
				customUsernameLoginTest, 
				customPassword,
				""));
		//CreateAccountTest
		accountMap.put("TC7", new AccountInfo(
				Utilities.genUsernameString(), 
				customPassword,
				customPID));
		accountMap.put("TC8", new AccountInfo(
				Utilities.genUsernameString(), 
				"",
				""));
		accountMap.put("TC9", new AccountInfo(
				Utilities.genUsernameString(), 
				customPassword,
				customPID));
		accountMap.put("TC10", new AccountInfo(
				Utilities.genUsernameString(), 
				customPassword,
				customPID));
		accountMap.put("TC11", new AccountInfo(
				Utilities.genUsernameString(), 
				customPassword,
				customPID));
	}
	
	public static AccountInfo getAccountInfo (String tcID) {
		AccountInfo account = accountMap.get(tcID);
		return account;
	}
}
