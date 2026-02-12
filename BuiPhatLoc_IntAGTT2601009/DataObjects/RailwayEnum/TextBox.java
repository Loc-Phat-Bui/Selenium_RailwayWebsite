package RailwayEnum;

public enum TextBox {
	USERNAME("username"),
    EMAIL("email"),
    PASSWORD("password"),
    CURRENT_PASSWORD("currentPassword"),
    NEW_PASSWORD("newPassword"),
    CONFIRM_PASSWORD("confirmPassword"),
    PID("pid");

    private final String value;

    TextBox(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
