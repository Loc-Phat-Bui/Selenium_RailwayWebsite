package AutomationExercise;

import Constant.Constant;

public class HomePage extends ProductPage{
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.AUTOMATION_EXERCISE_URL);
		return this;
	}
	
	public HomePage refresh() {
		Constant.WEBDRIVER.navigate().refresh();
		return this;
	}
}
