package dev.miklires.badrecipebook.client;

import dev.miklires.badrecipebook.core.RecipeUnlockPolicy;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BadRecipeBookClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("Bad Recipe Book");

    @Override
    public void onInitializeClient() {
        BadRecipeBookConfig config = ConfigStore.load(LOGGER);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (!RecipeUnlockPolicy.shouldUnlock(config.unlockAllRecipes, server.isSingleplayer())) {
                return;
            }

            int unlocked = handler.getPlayer().awardRecipes(server.getRecipeManager().getRecipes());
            if (config.showUnlockMessage && unlocked > 0) {
                handler.getPlayer().sendSystemMessage(Component.translatable(
                        "message.bad_recipe_book.unlocked", unlocked));
            }
            LOGGER.info("Unlocked {} recipes for {}", unlocked, handler.getPlayer().getScoreboardName());
        });
    }
}
