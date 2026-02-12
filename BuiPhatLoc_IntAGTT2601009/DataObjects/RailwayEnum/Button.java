package RailwayEnum;

public enum Button {
	LOGIN("login"),
    REGISTER("Register"),
    CHANGE_PASSWORD("Change Password"),
    RESET_PASSWORD("Reset Password"),
    SEND_INSTRUCTIONS("Send Instructions"),
    BOOK_TICKET("Book ticket");

    private final String value;

    Button(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
