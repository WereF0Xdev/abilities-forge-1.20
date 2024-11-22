package fox.mods.classes.classes;

public enum PlayerClass {
    HUMAN("Human", "§f"),
    PREDATOR("Predator", "§c"),
    GHOST("Ghost", "§7"),
    REPTILE("Reptile", "§a");

    private final String displayName;
    private final String displayColor;

    PlayerClass(String displayName, String displayColor) {
        this.displayName = displayName;
        this.displayColor = displayColor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public static PlayerClass fromString(String name) {
        for (PlayerClass playerClass : values()) {
            if (playerClass.name().equalsIgnoreCase(name) || playerClass.displayName.equalsIgnoreCase(name)) {
                return playerClass;
            }
        }
        return HUMAN;
    }
}

