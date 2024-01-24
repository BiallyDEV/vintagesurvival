package me.bially.vintagesurvival.tools;

import io.th0rgal.oraxen.api.OraxenItems;
import me.bially.vintagesurvival.VintageSurvival;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ToolsDurability implements Listener {

    public JavaPlugin plugin = VintageSurvival.getPlugin(VintageSurvival.class);

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemMainHand = player.getInventory().getItemInMainHand();
        String heldID = OraxenItems.getIdByItem(player.getInventory().getItemInMainHand());

        if (player.getGameMode() != GameMode.CREATIVE) {
            if (heldID == null) return;
            if (heldID.equals("flint_pickaxe") || heldID.equals("flint_axe")) {
                reduceFlintToolDurability(itemMainHand);
            }
            if (heldID.equals("flint_shovel") || heldID.equals("flint_hoe")) {
                reduceFlintShovelDurability(itemMainHand);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHoeUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        ItemStack itemMainHand = player.getInventory().getItemInMainHand();
        String heldID = OraxenItems.getIdByItem(player.getInventory().getItemInMainHand());

        if (heldID == null) return;
        if (!heldID.equals("flint_hoe")) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getHand() != EquipmentSlot.HAND) return;
        if (player.getGameMode() != GameMode.CREATIVE) {
            if (block.getType() == Material.DIRT || block.getType() == Material.GRASS_BLOCK
                    || block.getType() == Material.DIRT_PATH || block.getType() == Material.COARSE_DIRT
                    || block.getType() == Material.ROOTED_DIRT) {
                reduceFlintHoeUseDurability(itemMainHand);
            }
        }
    }

    public static void reduceFlintToolDurability(ItemStack item) {
        ItemMeta var = item.getItemMeta();
        if (var instanceof Damageable) {
            Damageable toolDurabilityMeta = (Damageable) var;
            int durability = toolDurabilityMeta.getDamage();
            int maxDurability = item.getType().getMaxDurability();
            if (durability + 1 <= maxDurability) {
                toolDurabilityMeta.setDamage(durability + 5);
                item.setItemMeta(toolDurabilityMeta);
            }
            if (durability >= maxDurability) {
                item.setAmount(0);
            }
        }
    }

    public static void reduceFlintShovelDurability(ItemStack item) {
        ItemMeta var = item.getItemMeta();
        if (var instanceof Damageable) {
            Damageable toolDurabilityMeta = (Damageable) var;
            int durability = toolDurabilityMeta.getDamage();
            int maxDurability = item.getType().getMaxDurability();
            if (durability + 1 <= maxDurability) {
                toolDurabilityMeta.setDamage(durability + 2);
                item.setItemMeta(toolDurabilityMeta);
            }
            if (durability >= maxDurability) {
                item.setAmount(0);
            }
        }
    }

    public static void reduceFlintHoeUseDurability(ItemStack item) {
        ItemMeta var = item.getItemMeta();
        if (var instanceof Damageable) {
            Damageable toolDurabilityMeta = (Damageable) var;
            int durability = toolDurabilityMeta.getDamage();
            int maxDurability = item.getType().getMaxDurability();
            if (durability + 1 <= maxDurability) {
                toolDurabilityMeta.setDamage(durability + 4);
                item.setItemMeta(toolDurabilityMeta);
            }
            if (durability >= maxDurability) {
                item.setAmount(0);
            }
        }
    }
}
