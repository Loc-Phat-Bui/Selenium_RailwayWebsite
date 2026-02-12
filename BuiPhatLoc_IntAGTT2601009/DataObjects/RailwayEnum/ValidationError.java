package RailwayEnum;

public enum ValidationError {
	PASSWORD("password/validation-error"),
    PID("pid/validation-error"),
    CONFIRM_PASSWORD("confirmPassword/validation-error");

    private final String value;

    ValidationError(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
