package me.bially.vintagesurvival.nutrition;

import io.th0rgal.oraxen.api.OraxenItems;
import me.bially.vintagesurvival.VintageSurvival;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class NutritionEvents implements Listener {
    private final VintageSurvival plugin;

    public NutritionEvents(VintageSurvival plugin) {
        this.plugin = plugin;
    }

    final TextComponent ese = Component.text("I should eat something else!")
            .color(NamedTextColor.RED);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.nutritionManager.loadOrCreateData(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.nutritionManager.removeNutrition(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();
        Material type = event.getItem().getType();
        ItemStack itemMainHand = player.getInventory().getItemInMainHand();
        NutritionData data = plugin.nutritionManager.getNutrition(uuid);

        if (!data.canEat(type)) {
            event.setCancelled(true);
            event.getPlayer().sendActionBar(ese);
            return;
        }
        if (type.equals(Material.APPLE)) {
            data.addCarbs(12);
            data.addProtein(1);
            data.addVitamins(6);
        }
        if (itemMainHand.getType() == Material.GOLDEN_APPLE) {
            data.addCarbs(12);
            data.addProtein(1);
            data.addVitamins(6);
        }
        if (itemMainHand.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
            data.addCarbs(12);
            data.addProtein(1);
            data.addVitamins(6);
        }
        if (type.equals(Material.MELON_SLICE)) {
            data.addCarbs(4);
            data.addProtein(1);
            data.addFat(1);
            data.addVitamins(3);
        }
        if (itemMainHand.getType() == Material.SWEET_BERRIES) {
            data.addCarbs(3);
            data.addVitamins(6);
        }
        if (itemMainHand.getType() == Material.GLOW_BERRIES) {
            data.addCarbs(3);
            data.addProtein(1);
            data.addVitamins(3);
        }
        if (itemMainHand.getType() == Material.CARROT) {
            data.addCarbs(6);
            data.addProtein(4);
            data.addFat(1);
            data.addVitamins(3);
        }
        if (itemMainHand.getType() == Material.GOLDEN_CARROT) {
            data.addCarbs(12);
            data.addProtein(6);
            data.addFat(2);
            data.addVitamins(6);
        }
        if (itemMainHand.getType() == Material.POTATO) {
            data.addCarbs(4);
            data.addProtein(1);
        }
        if (itemMainHand.getType() == Material.BAKED_POTATO) {
            data.addCarbs(20);
            data.addProtein(5);
        }
        if (itemMainHand.getType() == Material.BEETROOT) {
            data.addCarbs(3);
            data.addProtein(1);
            data.addVitamins(1);
        }
        if (itemMainHand.getType() == Material.DRIED_KELP) {
            data.addProtein(1);
        }
        if (itemMainHand.getType() == Material.BEEF) {
            data.addProtein(8);
            data.addFat(7);
        }
        if (itemMainHand.getType() == Material.COOKED_BEEF) {
            data.addProtein(24);
            data.addFat(16);
        }
        if (itemMainHand.getType() == Material.PORKCHOP) {
            data.addProtein(7);
            data.addFat(8);
        }
        if (itemMainHand.getType() == Material.COOKED_PORKCHOP) {
            data.addProtein(16);
            data.addFat(24);
        }
        if (itemMainHand.getType() == Material.MUTTON) {
            data.addProtein(7);
            data.addFat(3);
        }
        if (itemMainHand.getType() == Material.COOKED_MUTTON) {
            data.addProtein(21);
            data.addFat(9);
        }
        if (itemMainHand.getType() == Material.CHICKEN) {
            data.addProtein(9);
            data.addFat(1);
        }
        if (itemMainHand.getType() == Material.COOKED_CHICKEN) {
            data.addProtein(27);
            data.addFat(3);
        }
        if (itemMainHand.getType() == Material.RABBIT) {
            data.addProtein(11);
            data.addFat(4);
        }
        if (itemMainHand.getType() == Material.COOKED_RABBIT) {
            data.addProtein(19);
            data.addFat(6);
        }
        if (itemMainHand.getType() == Material.COD) {
            data.addProtein(7);
            data.addFat(1);
        }
        if (itemMainHand.getType() == Material.COOKED_COD) {
            data.addProtein(16);
            data.addFat(3);
        }
        if (itemMainHand.getType() == Material.SALMON) {
            data.addProtein(4);
            data.addFat(4);
        }
        if (itemMainHand.getType() == Material.COOKED_SALMON) {
            data.addProtein(10);
            data.addFat(14);
        }
        if (itemMainHand.getType() == Material.TROPICAL_FISH) {
            data.addProtein(3);
            data.addFat(1);
        }
        if (itemMainHand.getType() == Material.BREAD) {
            data.addCarbs(19);
            data.addProtein(4);
            data.addFat(1);
        }
        if (itemMainHand.getType() == Material.COOKIE) {
            data.addCarbs(2);
            data.addFat(3);
        }
        if (itemMainHand.getType() == Material.PUMPKIN_PIE) {
            data.addCarbs(18);
            data.addProtein(4);
            data.addFat(18);
        }
        if (itemMainHand.getType() == Material.MUSHROOM_STEW) {
            data.addCarbs(7);
            data.addProtein(4);
            data.addFat(15);
        }
        if (itemMainHand.getType() == Material.BEETROOT_SOUP) {
            data.addCarbs(18);
            data.addProtein(6);
            data.addVitamins(6);
        }
        if (itemMainHand.getType() == Material.RABBIT_STEW) {
            data.addCarbs(26);
            data.addProtein(27);
            data.addFat(8);
            data.addVitamins(4);
        }
        if (itemMainHand.getType() == Material.SUSPICIOUS_STEW) {
            data.addCarbs(7);
            data.addProtein(4);
            data.addFat(15);
            data.addVitamins(5);
        }
        if (itemMainHand.getType() == Material.HONEY_BOTTLE) {
            data.addCarbs(29);
            data.addProtein(1);
        }
        if (itemMainHand.getType() == Material.MILK_BUCKET) {
            data.addCarbs(1);
            data.addProtein(18);
            data.addFat(2);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        NutritionData data = plugin.nutritionManager.getNutrition(player.getUniqueId());
        data.addCarbs(100);
        data.addProtein(100);
        data.addFat(100);
        data.addVitamins(100);
        data.clearLastFood();
    }
}
