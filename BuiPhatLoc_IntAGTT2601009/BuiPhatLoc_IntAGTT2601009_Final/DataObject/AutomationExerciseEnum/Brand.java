package AutomationExerciseEnum;

public enum Brand {
    POLO("Polo"),
    H_M("H&M"),
    MADAME("Madame"),
    MAST_HARBOUR("Mast & Harbour"),
    BABYHUG("Babyhug"),
    ALLEN_SOLLY_JUNIOR("Allen Solly Junior"),
    KOOKIE_KIDS("Kookie Kids"),
    BIBA("Biba");

    private final String value;

    Brand(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}