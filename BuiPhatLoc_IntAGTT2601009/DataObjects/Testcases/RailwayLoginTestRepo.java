package Testcases;

import java.util.HashMap;
import java.util.Map;

import Account.Account;

public class RailwayLoginTestRepo {
	
	private static class RailwayLoginTest_TestcaseInfo {
		String tcID;
		String tcDescription;
		String[] tcSteps;
		String tcUsername;
		String tcPassword;
		
		public RailwayLoginTest_TestcaseInfo(String tcID, String tcDescription, String[] tcSteps, String tcUsername, String tcPassword) {
			this.tcID = tcID;
			this.tcDescription = tcDescription;
			this.tcSteps = tcSteps;
			this.tcUsername = tcUsername;
			this.tcPassword = tcPassword;
		}
	}
	
	
	private static final Map<String, RailwayLoginTest_TestcaseInfo> tcMap = new HashMap<String, RailwayLoginTest_TestcaseInfo>();
	
	static {
        // TC1: Valid Login
        tcMap.put("TC1", new RailwayLoginTest_TestcaseInfo(
            "TC1", 
            "User can log into Railway with valid username and password", 
            new String[] {
                "1. Navigate to QA Railway Website",
                "2. Click on \"Login\" tab",
                "3. Enter valid Email and Password",
                "4. Click on \"Login\" button"
            },
            Account.validUSERNAME,
            Account.validPASSWORD
        ));

        // TC2: Blank Username
        tcMap.put("TC2", new RailwayLoginTest_TestcaseInfo(
            "TC2", 
            "User cannot login with blank \"Username\" textbox", 
            new String[] {
                "1. Navigate to QA Railway Website",
                "2. Click on \"Login\" tab",
                "3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox",
                "4. Click on \"Login\" button"
            },
            Account.blankUSERNAME,
            Account.validPASSWORD
        ));

        // TC3: Invalid Password
        tcMap.put("TC3", new RailwayLoginTest_TestcaseInfo(
            "TC3", 
            "User cannot log into Railway with invalid password", 
            new String[] {
                "1. Navigate to QA Railway Website",
                "2. Click on \"Login\" tab",
                "3. Enter valid Email and invalid Password",
                "4. Click on \"Login\" button"
            },
            Account.validUSERNAME,
            Account.invalidPASSWORD
        ));

        // TC4: Repeated wrong password
        tcMap.put("TC4", new RailwayLoginTest_TestcaseInfo(
            "TC4", 
            "System shows message when user enters wrong password many times", 
            new String[] {
                "1. Navigate to QA Railway Website",
                "2. Click on \"Login\" tab",
                "3. Enter valid information into \"Username\" textbox except \"Password\" textbox.",
                "4. Click on \"Login\" button",
                "5. Repeat step 3 and 4 three more times."
            },
            Account.validUSERNAME,
            Account.invalidPASSWORD
        ));

        // TC5: Account not activated
        tcMap.put("TC5", new RailwayLoginTest_TestcaseInfo(
            "TC5", 
            "User can't login with an account hasn't been activated", 
            new String[] {
                "Pre-condition: a not-active account is existing",
                "1. Navigate to QA Railway Website",
                "2. Click on \"Login\" tab",
                "3. Enter information for the account that hasn't been activated.",
                "4. Click on \"Login\" button"
            },
            Account.validUSERNAME,
            Account.validPASSWORD
        ));
    }
	
	public static void printTestcaseInfo (String tcID) {
		RailwayLoginTest_TestcaseInfo tc = tcMap.get(tcID);
		
		if(tc != null) {
			System.out.println(tc.tcID + " - " + tc.tcDescription);
			for (int i = 0; i < tc.tcSteps.length; i++) {
				System.out.println("Step: " + tc.tcSteps[i]);
			}
		}
	}
	
	public static String getTestCaseUsername(String tcID) {
		RailwayLoginTest_TestcaseInfo tc = tcMap.get(tcID);
		return tc.tcUsername;
	}
	
	public static String getTestCasePassword(String tcID) {
		RailwayLoginTest_TestcaseInfo tc = tcMap.get(tcID);
		return tc.tcPassword;
	}
}
