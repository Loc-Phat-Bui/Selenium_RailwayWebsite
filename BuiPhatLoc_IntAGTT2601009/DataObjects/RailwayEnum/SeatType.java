package RailwayEnum;

public enum SeatType {
    HARD_SEAT(1, "Hard seat"),
    SOFT_SEAT(2, "Soft seat"),
    SOFT_SEAT_AC(3, "Soft seat with air conditioner"),
    HARD_BED(4, "Hard bed"),
    SOFT_BED(5, "Soft bed"),
    SOFT_BED_AC(6, "Soft bed with air conditioner");

    private final int value;
    private final String description;

    SeatType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static SeatType fromValue(int value) {
        for (SeatType type : SeatType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown SeatType value: " + value);
    }
}
