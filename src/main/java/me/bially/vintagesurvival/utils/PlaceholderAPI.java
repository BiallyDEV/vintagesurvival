package me.bially.vintagesurvival.utils;

import me.bially.vintagesurvival.nutrition.NutritionData;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;


public class PlaceholderAPI extends PlaceholderExpansion {

    private HashMap<UUID,NutritionData> playerNutritions = new HashMap<>();
    @Override
    public @NotNull String getIdentifier() {
        return "vintagesurvival";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Bially";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {

        if (player == null) return "";

        NutritionData data = playerNutritions.get(player.getUniqueId());
        return switch (params) {
            case "protein" -> String.valueOf(data.getProtein());
            case "carbs" -> String.valueOf(data.getCarbs());
            case "fat" -> String.valueOf(data.getFat());
            case "vitamins" -> String.valueOf(data.getVitamins());
            default -> "";
        };

    }
}
