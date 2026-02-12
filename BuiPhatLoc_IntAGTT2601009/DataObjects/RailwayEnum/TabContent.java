package RailwayEnum;

public enum TabContent {
	REGISTER("content", "/Account/Register.cshtml"),
    FORGOT_PASSWORD("content", "/Account/ForgotPassword.cshtml");

	private final String div;
    private final String href;

    TabContent(String div, String href) {
        this.div = div;
        this.href = href;
    }

    public String getDiv() {
        return this.div;
    }
    
    public String getHref() {
    	return this.href;
    }
}
