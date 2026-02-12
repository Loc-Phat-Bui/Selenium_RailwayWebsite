package RailwayEnum;

public enum TabMenu {
	LOGIN("menu", "/Account/Login.cshtml"),
    LOGOUT("menu", "/Account/Logout"),
    CHANGE_PASSWORD("menu", "/Account/ChangePassword.cshtml"),
    REGISTER("menu", "/Account/Register.cshtml"),
    FAQ("menu", "/Page/FAQ.cshtml"),
    BOOK_TICKET("menu", "/Page/BookTicketPage.cshtml"),
    TIMETABLE("menu", "/TrainTimeListPage.cshtml"),
    MY_TICKET("menu", "/Page/ManageTicket.cshtml");

	private final String div;
    private final String href;

    TabMenu(String div, String href) {
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
