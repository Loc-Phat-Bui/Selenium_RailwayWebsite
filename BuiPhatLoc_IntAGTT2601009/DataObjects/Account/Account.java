package Account;

import java.util.HashMap;
import java.util.Map;

public class Account {
	
	public static class AccountInfo {
		private String username;
		private String password;
		
		public AccountInfo(String username, String password) {
			this.username = username;
			this.password = password;
		}
		
		public String getUsername() {return this.username;}
		public String getPassword() {return this.password;}
	}
	
	public static final String sceValidLogin = "valid login";
	public static final String sceBlankUsername = "blank username"; 
	public static final String sceInvalidPassword = "invalid password";
	
	private static final Map<String, AccountInfo> accountMap = new HashMap<String, Account.AccountInfo>();
	
	static {
		accountMap.put(sceValidLogin, new AccountInfo(
				"phatlocbui@gmail.com", 
				"Loc@123456"));
		
		accountMap.put(sceBlankUsername, new AccountInfo(
				"", 
				"Loc@123456"));
		
		accountMap.put(sceInvalidPassword, new AccountInfo(
				"phatlocbui@gmail.com", 
				"123456789"));
		
	}
	
	public static AccountInfo getAccountInfo (String scenario) {
		AccountInfo account = accountMap.get(scenario);
		return account;
	}
}
