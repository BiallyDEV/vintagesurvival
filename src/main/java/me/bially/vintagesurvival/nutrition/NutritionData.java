package me.bially.vintagesurvival.nutrition;

import me.bially.vintagesurvival.utils.CustomConfig;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NutritionData {
    private final UUID uuid;
    private int carbs;
    private int protein;
    private int fat;
    private int vitamins;
    private final CustomConfig config;
    private List<Material> lastFood = new ArrayList<>();

    public NutritionData(UUID uuid, int carbs, int protein, int fat, int vitamins, CustomConfig config) {
        this.uuid = uuid;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.vitamins = vitamins;
        this.config = config;
    }

    public NutritionData(UUID uuid, int carbs, int protein, int fat, int vitamins, CustomConfig config, List<Material> lastFood) {
        this.uuid = uuid;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.vitamins = vitamins;
        this.config = config;
        this.lastFood = lastFood;
    }

    public void save(JavaPlugin main) {
        config.getConfig().set("Nutrition.Carbs",carbs);
        config.getConfig().set("Nutrition.Protein",protein);
        config.getConfig().set("Nutrition.Fat",fat);
        config.getConfig().set("Nutrition.Vitamins",vitamins);
        for (int x = 0;x<lastFood.size();x++) {
            config.getConfig().set("LastFood."+x,lastFood.get(x).toString());
        }
        config.saveAsync(main);
    }

    public void saveSync() {
        config.getConfig().set("Nutrition.Carbs",carbs);
        config.getConfig().set("Nutrition.Protein",protein);
        config.getConfig().set("Nutrition.Fat",fat);
        config.getConfig().set("Nutrition.Vitamins",vitamins);
        for (int x = 0;x<lastFood.size();x++) {
            config.getConfig().set("LastFood."+x,lastFood.get(x).toString());
        }
        config.save();
    }

    public void reduceNutrition() {
        if (carbs>=1) {
            carbs-=1;
        }
        if (protein>=3) {
            protein-=3;
        }
        if (fat>=1) {
            fat-=1;
        }
        if (vitamins>=3) {
            vitamins-=3;
        }
    }

    public void addCarbs(int amount) {
        carbs+=amount;
        if (carbs>100) {
            carbs=100;
        }
    }

    public void addProtein(int amount) {
        protein+=amount;
        if (protein>100) {
            protein=100;
        }
    }

    public void addFat(int amount) {
        fat+=amount;
        if (fat>100) {
            fat=100;
        }
    }

    public void addVitamins(int amount) {
        vitamins+=amount;
        if (vitamins>100) {
            vitamins=100;
        }
    }



    public static NutritionData buildData(UUID uuid,CustomConfig config) {
        int carbs = config.getConfig().getInt("Nutrition.Carbs");
        int protein = config.getConfig().getInt("Nutrition.Protein");
        int fat = config.getConfig().getInt("Nutrition.Fat");
        int vitamins = config.getConfig().getInt("Nutrition.Vitamins");
        List<Material> lastFood = new ArrayList<>();
        if (config.getConfig().contains("LastFood")) {
            for (String s : config.getConfig().getConfigurationSection("LastFood").getKeys(false)){
                lastFood.add(Material.valueOf(config.getString("LastFood."+s)));
            }
        }
        return new NutritionData(uuid,carbs,protein,fat,vitamins,config,lastFood);
    }

    public void setLastFood(List<Material> lastFood) {
        this.lastFood = lastFood;
    }

    public List<Material> getLastFood() {
        return lastFood;
    }
    public void clearLastFood() {
        lastFood.clear();
    }
    public int getCarbs() {
        return carbs;
    }
    public int getProtein() {
        return protein;
    }
    public int getFat() {
        return fat;
    }
    public int getVitamins() {
        return vitamins;
    }

    public boolean canEat(Material material) {
        if (lastFood.contains(material)) {
            int amount = 0;
            for (Material m : lastFood) {
                if (m==material) {
                    amount++;
                }
            }
            if (amount>3) {
                return false;
            }
        }
        return true;
    }

    public void addRecentFood(Material type) {
        lastFood.add(0,type);
        if (lastFood.size()>5) {
            lastFood.remove(4);
        }
    }
}
