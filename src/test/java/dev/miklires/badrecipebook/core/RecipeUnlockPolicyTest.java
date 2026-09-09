package dev.miklires.badrecipebook.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecipeUnlockPolicyTest {
    @Test
    void unlocksWhenEnabledInSingleplayer() {
        assertTrue(RecipeUnlockPolicy.shouldUnlock(true, true));
    }

    @Test
    void doesNotModifyRemoteServers() {
        assertFalse(RecipeUnlockPolicy.shouldUnlock(true, false));
    }

    @Test
    void respectsDisabledSetting() {
        assertFalse(RecipeUnlockPolicy.shouldUnlock(false, true));
    }
}
