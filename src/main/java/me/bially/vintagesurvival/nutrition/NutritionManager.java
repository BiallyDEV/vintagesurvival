package me.bially.vintagesurvival.nutrition;

import me.bially.vintagesurvival.VintageSurvival;
import me.bially.vintagesurvival.utils.CustomConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class NutritionManager {
    private final VintageSurvival plugin;
    public NutritionManager(VintageSurvival plugin) {
        this.plugin = plugin;
    }

    private HashMap<UUID,NutritionData> playerNutritions = new HashMap<>();

    final TextComponent carbsText = Component.text("Potrzebuje weglowodanow")
            .color(TextColor.color(0xE21719));
    final TextComponent proteinText = Component.text("Potrzebuje bialka")
            .color(TextColor.color(0xE21719));
    final TextComponent fatText = Component.text("Potrzebuje tluszczu")
            .color(TextColor.color(0xE21719));
    final TextComponent vitaminsText = Component.text("Potrzebuje witamin")
            .color(TextColor.color(0xE21719));

    public void addNutrition(UUID uuid,NutritionData nutritionData) {
        playerNutritions.put(uuid,nutritionData);
    }
    public void removeNutrition(UUID uuid) {
        if (playerNutritions.containsKey(uuid)) {
            playerNutritions.get(uuid).save(plugin);
            playerNutritions.remove(uuid);
        }
    }
    public void removeNutritionSync(Player player) {
        if (playerNutritions.containsKey(player.getUniqueId())) {
            playerNutritions.get(player.getUniqueId()).saveSync();
            playerNutritions.remove(player.getUniqueId());
        }
    }
    public NutritionData getNutrition(UUID uuid) {
        return playerNutritions.get(uuid);
    }


    public void loadTimers() {
        reduceNutrition();
        checkNutrition();
    }
    //Reduce points off nutrition every 3 minutes
    private void reduceNutrition() {
        Bukkit.getScheduler().runTaskTimer(plugin,()-> {
            for (UUID uuid : playerNutritions.keySet()) {
                if (Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).getGameMode() != GameMode.SURVIVAL) {
                    return;
                }
                playerNutritions.get(uuid).reduceNutrition();
                checkStats(uuid);
            }
        },0,20*3*60);
    }
    //Apply effects(check each 10 seconds)
    private void checkNutrition() {
        Bukkit.getScheduler().runTaskTimer(plugin,()-> {
            for (UUID uuid : playerNutritions.keySet()) {
                if (Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).getGameMode() != GameMode.SURVIVAL) {
                    return;
                }
                NutritionData data = playerNutritions.get(uuid);
                if (data.getCarbs()<20) {
                    addEffect(uuid, PotionEffectType.SLOW);
                }
                if (data.getProtein()<20) {
                    addEffect(uuid,PotionEffectType.WEAKNESS);
                }
                if (data.getFat()<20) {
                    addEffect(uuid,PotionEffectType.SLOW);
                }
                if (data.getVitamins()<20) {
                    addEffect(uuid, PotionEffectType.SLOW_DIGGING);
                }
            }
        },0,20*10);
    }
    private void addEffect(UUID uuid, PotionEffectType type) {
        Player p = Bukkit.getPlayer(uuid);
        if (p==null) {
            return;
        }
        p.addPotionEffect(new PotionEffect(type,20*10,0));
    }

    public void loadOrCreateData(Player player){
        CustomConfig config = new CustomConfig("player-data",player.getUniqueId().toString(),plugin);
        if (!config.getConfig().contains("Nutrition")){
            config.getConfig().set("Nutrition.Carbs",100);
            config.getConfig().set("Nutrition.Protein",100);
            config.getConfig().set("Nutrition.Fat",100);
            config.getConfig().set("Nutrition.Vitamins",100);
            config.save();
        }
        //Pass config to the manager
        addNutrition(player.getUniqueId(),NutritionData.buildData(player.getUniqueId(),config));
    }
    private void checkStats(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            NutritionData data = getNutrition(uuid);
            if (data == null) {
                return;
            }
            if (data.getCarbs() < 30) {
                new BukkitRunnable() {
                    private int i = 0;

                    @Override
                    public void run() {
                        if (i >= 20) {
                            cancel();
                        }
                        ++i;
                        player.sendActionBar(carbsText);
                    }
                }.runTaskTimer(plugin, 2L, 0L);
            }
            if (data.getProtein() < 30) {
                new BukkitRunnable() {
                    private int i = 0;

                    @Override
                    public void run() {
                        if (i >= 20) {
                            cancel();
                        }
                        ++i;
                        player.sendActionBar(proteinText);
                    }
                }.runTaskTimer(plugin, 2L, 0L);
            }
            if (data.getFat() < 30) {
                new BukkitRunnable() {
                    private int i = 0;

                    @Override
                    public void run() {
                        if (i >= 20) {
                            cancel();
                        }
                        ++i;
                        player.sendActionBar(fatText);
                    }
                }.runTaskTimer(plugin, 2L, 0L);
            }
            if (data.getVitamins() < 30) {
                new BukkitRunnable() {
                    private int i = 0;

                    @Override
                    public void run() {
                        if (i >= 20) {
                            cancel();
                        }
                        ++i;
                        player.sendActionBar(vitaminsText);
                    }
                }.runTaskTimer(plugin, 2L, 0L);
            }
        }
    }
}
