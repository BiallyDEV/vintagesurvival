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

        if (player == null) {
            return "";
        }
        if (params.equals("carbslvl")) {
            for (UUID uuid : playerNutritions.keySet()) {
                NutritionData data = playerNutritions.get(uuid);
                return String.valueOf(data.getCarbs());
            }
        }

            return null;
    }
}
