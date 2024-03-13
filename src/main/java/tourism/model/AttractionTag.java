package tourism.model;

public enum AttractionTag {
    MAD_DRIKKE("Mad & Drikke"),
    BAR("Bar"),
    NATUR("Natur"),
    MUSEUM("Museum"),
    EVENTYRLIG("Eventyrlig"),
    UDSIGT("Udsigt"),
    AKTIV("Aktiv"),
    HISTORISK("Historisk"),
    KULTUR("Kultur"),
    KUNST("Kunst"),
    FAMILIEVENLIG("Familievenlig"),
    UNDERHOLDNING("Underholdning");

    private final String displayValue;

    AttractionTag(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
