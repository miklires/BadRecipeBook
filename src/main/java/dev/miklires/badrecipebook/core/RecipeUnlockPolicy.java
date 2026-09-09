package dev.miklires.badrecipebook.core;

public final class RecipeUnlockPolicy {
    private RecipeUnlockPolicy() {
    }

    public static boolean shouldUnlock(boolean enabled, boolean singleplayer) {
        return enabled && singleplayer;
    }
}
