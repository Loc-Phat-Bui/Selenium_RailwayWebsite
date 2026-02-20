package AutomationExerciseEnum;

public enum NavBar {
	HOME("/"),
	PRODUCT("products"),
	CART("view cart"),
	LOGIN("login"),
	TESTCASE("test_cases"),
	APITEST("api_list"),
	VIDEO("https://www.youtube.com/c/AutomationExercise"),
	CONTACT("contact_us"),
	LOGOUT("logout"),
	DELETE("delete_account");
	
	private final String value;
	
	NavBar(String value) {
		this.value = value;
	}
	
	public String  getValue() {
		return this.value;
	}
}
