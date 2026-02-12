package RailwayEnum;

public enum Location {
	SAI_GON("Sài Gòn"),
    HUE("Huế"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    PHAN_THIET("Phan Thiết"),
    QUANG_NGAI("Quảng Ngãi");

    private final String displayName;

    Location(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
