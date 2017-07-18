package com.ferdielik.entity;

public enum StationStatus {
    ACTIVE("Aktif"),
    NONACTIVE("Aktif Degil");

    private String label;

    StationStatus(String label) {
        this.label = label;
    }

    public static StationStatus byLabel(String label) {
        for (StationStatus status : values())
            if (label.equals(status.getLabel()))
                return status;

        return NONACTIVE;
    }

    public String getLabel() {
        return label;
    }
}
