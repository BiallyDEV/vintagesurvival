package me.bially.vintagesurvival;

import io.th0rgal.oraxen.api.OraxenItems;
import me.bially.vintagesurvival.commands.VSCommands;
import me.bially.vintagesurvival.ntp.NTPEvents;
import me.bially.vintagesurvival.nutrition.NutritionEvents;
import me.bially.vintagesurvival.nutrition.NutritionManager;
import me.bially.vintagesurvival.tools.Knife;
import me.bially.vintagesurvival.tools.SpecialDrops;
import me.bially.vintagesurvival.tools.ToolsDurability;
import me.bially.vintagesurvival.utils.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class VintageSurvival extends JavaPlugin {
    public static VintageSurvival instance;
    public NutritionManager nutritionManager = new NutritionManager(this);
    public ArrayList<Player> carbs = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        enable();
    }

    @Override
    public void onDisable() {
        this.getLogger().info("VintageSurvival disabled");
        savePlayersSync();
        instance = null;
    }

    private void enable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        if (pluginManager.isPluginEnabled("Oraxen")) {
            Bukkit.getScheduler().runTaskLater(this, () -> {
                loadConfig();
                VSCommands commands = new VSCommands(this);
                getCommand("makro").setExecutor(commands);
                getCommand("craftcrafting").setExecutor(commands);
                nutritionManager.loadTimers();
                loadPlayers();
                if (pluginManager.isPluginEnabled("PlaceholderAPI"))
                    new PlaceholderAPI().register();

                getServer().getPluginManager().registerEvents(new NutritionEvents(this),this);
                Bukkit.getPluginManager().registerEvents(new Firewood(), this);
                Bukkit.getPluginManager().registerEvents(new Knife(), this);
                Bukkit.getPluginManager().registerEvents(new SpecialDrops(), this);
                Bukkit.getPluginManager().registerEvents(new ToolsDurability(), this);
                Bukkit.getPluginManager().registerEvents(new NTPEvents(this), this);

                OraxenItems.loadItems();
                this.getLogger().info("VintageSurvival enabled");
            }, 20L);
        }
        else Bukkit.getScheduler().runTaskLater(this, this::enable, 20L);
    }

    public static VintageSurvival getInstance() {
        return instance;
    }

    private void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }


    private void loadPlayers(){
        for (Player p : Bukkit.getOnlinePlayers()){
            nutritionManager.loadOrCreateData(p);
        }
    }

    private void savePlayersSync(){
        for (Player p : Bukkit.getOnlinePlayers()){
            nutritionManager.removeNutritionSync(p);
        }
    }
}
