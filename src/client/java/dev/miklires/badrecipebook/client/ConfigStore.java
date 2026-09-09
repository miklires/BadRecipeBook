package dev.miklires.badrecipebook.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

final class ConfigStore {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("bad-recipe-book.json");

    private ConfigStore() {
    }

    static BadRecipeBookConfig load(Logger logger) {
        if (!Files.isRegularFile(PATH)) {
            BadRecipeBookConfig defaults = new BadRecipeBookConfig();
            save(defaults, logger);
            return defaults;
        }

        try (Reader reader = Files.newBufferedReader(PATH, StandardCharsets.UTF_8)) {
            BadRecipeBookConfig config = GSON.fromJson(reader, BadRecipeBookConfig.class);
            if (config != null) {
                return config;
            }
        } catch (Exception exception) {
            quarantineInvalidConfig(logger);
            logger.warn("Could not read {}; defaults will be used", PATH, exception);
        }

        BadRecipeBookConfig defaults = new BadRecipeBookConfig();
        save(defaults, logger);
        return defaults;
    }

    private static void save(BadRecipeBookConfig config, Logger logger) {
        try {
            Files.createDirectories(PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(PATH, StandardCharsets.UTF_8)) {
                GSON.toJson(config, writer);
            }
        } catch (IOException exception) {
            logger.warn("Could not write default config to {}", PATH, exception);
        }
    }

    private static void quarantineInvalidConfig(Logger logger) {
        Path backup = PATH.resolveSibling(PATH.getFileName() + ".invalid");
        try {
            Files.move(PATH, backup, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            logger.warn("Could not preserve invalid config as {}", backup, exception);
        }
    }
}
