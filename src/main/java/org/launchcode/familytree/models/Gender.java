package org.launchcode.familytree.models;

public enum Gender {
    PREFERNOTTOSAY ("Prefer not to say"),
    MAN ("Man"),
    WOMAN ("Woman"),
    TRANSGENDER ("Transgender"),
    NONBINARY ("Non-binary/non-conforming");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
