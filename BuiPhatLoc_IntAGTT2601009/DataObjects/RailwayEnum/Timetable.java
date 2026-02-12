package RailwayEnum;

public enum Timetable {
	CHECK_PRICE("timetable/TicketPricePage.cshtml"),
    BOOK_TICKET("timetable/BookTicketPage.cshtml");

    private final String value;

    Timetable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
