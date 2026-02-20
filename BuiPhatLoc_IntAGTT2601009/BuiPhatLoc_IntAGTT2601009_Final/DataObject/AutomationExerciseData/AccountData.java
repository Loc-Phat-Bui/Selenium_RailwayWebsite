package AutomationExerciseData;

import java.util.HashMap;
import java.util.Map;

import Common.Utilities;

public class AccountData {

	public static class Account {
		// Personal Information fields
		private String title;
		private String name;
		private String email;
		private String password;
		private String day;
		private String month;
		private String year;
		private boolean newsletter;
		private boolean specialOffers;
		// Address Information fields
		private String firstName;
		private String lastName;
		private String company;
		private String address;
		private String address2;
		private String country;
		private String state;
		private String city;
		private String zipcode;
		private String mobileNumber;
		// Review
		private String review;

		public Account(String title, String name, String email, String password, String day, String month,
				String year, boolean newsletter, boolean specialOffers, String firstName, String lastName,
				String company, String address, String address2, String country, String state, String city,
				String zipcode, String mobileNumber, String review) {
			this.title = title;
			this.name = name;
			this.email = email;
			this.password = password;
			this.day = day;
			this.month = month;
			this.year = year;
			this.newsletter = newsletter;
			this.specialOffers = specialOffers;
			this.firstName = firstName;
			this.lastName = lastName;
			this.company = company;
			this.address = address;
			this.address2 = address2;
			this.country = country;
			this.state = state;
			this.city = city;
			this.zipcode = zipcode;
			this.mobileNumber = mobileNumber;
			this.review = review;
		}

		// --- GETTERS ---
		public String getTitle() { return this.title; }
		public String getName() { return this.name; }
		public String getEmail() { return this.email; }
		public String getPassword() { return this.password; }
		public String getDay() { return this.day; }
		public String getMonth() { return this.month; }
		public String getYear() { return this.year; }
		public boolean isNewsletter() { return this.newsletter; }
		public boolean isSpecialOffers() { return this.specialOffers; }
		public String getFirstName() { return this.firstName; }
		public String getLastName() { return this.lastName; }
		public String getCompany() { return this.company; }
		public String getAddress() { return this.address; }
		public String getAddress2() { return this.address2; }
		public String getCountry() { return this.country; }
		public String getState() { return this.state; }
		public String getCity() { return this.city; }
		public String getZipcode() { return this.zipcode; }
		public String getMobileNumber() { return this.mobileNumber; }
		public String getReview() { return this.review; }

		// --- SETTERS ---
		public void setTitle(String title) { this.title = title; }
		public void setName(String name) { this.name = name; }
		public void setEmail(String email) { this.email = email; }
		public void setPassword(String password) { this.password = password; }
		public void setDay(String day) { this.day = day; }
		public void setMonth(String month) { this.month = month; }
		public void setYear(String year) { this.year = year; }
		public void setNewsletter(boolean newsletter) { this.newsletter = newsletter; }
		public void setSpecialOffers(boolean specialOffers) { this.specialOffers = specialOffers; }
		public void setFirstName(String firstName) { this.firstName = firstName; }
		public void setLastName(String lastName) { this.lastName = lastName; }
		public void setCompany(String company) { this.company = company; }
		public void setAddress(String address) { this.address = address; }
		public void setAddress2(String address2) { this.address2 = address2; }
		public void setCountry(String country) { this.country = country; }
		public void setState(String state) { this.state = state; }
		public void setCity(String city) { this.city = city; }
		public void setZipcode(String zipcode) { this.zipcode = zipcode; }
		public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
		public void setReview(String review) { this.review = review; }
	}
	
	private static final String fisrtName1 = GenUsername.genBiotic();
	private static final String lastName1 = GenUsername.genAbiotic();
	private static final String fisrtName2 = GenUsername.genBiotic();
	private static final String lastName2 = GenUsername.genAbiotic();
	private static final String password = "BeTheOne@20170903_20180826";
	private static final Map<String, Account> accountMap = new HashMap<String, AccountData.Account>();

	static {
		accountMap.put("TC_00", new Account(
				"Mr.", 
				"LokeLunarius", 
				"Loke@mail.com", 
				password, 
				"24", "June", "1986", true, true,
				"Loke", "Lunarius", "LogiGear", "1730 S Amphlett Blvd # 200", "1730 S Amphlett Blvd # 200", 
				"United States", "California", "San Mateo", "94402", "0987654321",
				"Product is fantasty. 5/5 star no drama"));
		accountMap.put("TC_02_Chrome", new Account(
				"Mr.", 
				Utilities.splitEmail(GenUsername.genUsernameBuild(fisrtName1, lastName1))[0], 
				GenUsername.genUsernameBuild(fisrtName1, lastName1), 
				password, 
				"24", "June", "1986", true, true,
				fisrtName1, lastName1, "LogiGear", " 1730 S Amphlett Blvd # 200", " 1730 S Amphlett Blvd # 200", 
				"United States", "California", "San Mateo", "94402", "0987654321",
				"Product is fantasty. 5/5 star no drama"));
		accountMap.put("TC_02_Firefox", new Account(
				"Mr.", 
				Utilities.splitEmail(GenUsername.genUsernameBuild(fisrtName2, lastName2))[0], 
				GenUsername.genUsernameBuild(fisrtName2, lastName2), 
				password, 
				"24", "June", "1986", true, true,
				fisrtName2, lastName2, "LogiGear", " 1730 S Amphlett Blvd # 200", " 1730 S Amphlett Blvd # 200", 
				"United States", "California", "San Mateo", "94402", "0987654321",
				"Product is fantasty. 5/5 star no drama"));
	}

	public static Account getAccountInfo(String tcID) {
		return accountMap.get(tcID);
	}
}