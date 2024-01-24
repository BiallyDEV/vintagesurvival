package me.bially.vintagesurvival.tools;

import io.th0rgal.oraxen.api.OraxenBlocks;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.mechanics.provided.gameplay.stringblock.StringBlockMechanic;
import me.bially.vintagesurvival.VintageSurvival;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Knife implements Listener {

    public JavaPlugin plugin = VintageSurvival.getPlugin(VintageSurvival.class);

    private final List<Block> blockCooldownList = new ArrayList<>();

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void KnifeUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        String heldID = OraxenItems.getIdByItem(player.getInventory().getItemInMainHand());
        Block targetBlock = player.getTargetBlock(null, 100);
        Location location = targetBlock.getLocation();

        ItemStack cattails_item = OraxenItems.getItemById("cattails_item").build();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getHand() != EquipmentSlot.HAND) return;
        if (heldID == null) return;
        if (!heldID.equals("stone_knife")) return;

        if (targetBlock.getType().equals(Material.TRIPWIRE)) {
            StringBlockMechanic stringMechanic = OraxenBlocks.getStringMechanic(targetBlock);
            if (stringMechanic.getItemID().equals("cattails")) {
                OraxenBlocks.place("cattails_cut", location);
                player.getWorld().dropItemNaturally(targetBlock.getRelative(player.getFacing().getOppositeFace()).getLocation(), cattails_item);
                event.getPlayer().swingMainHand();
                blockCooldownList.add(block);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    OraxenBlocks.place("cattails", location);
                    blockCooldownList.remove(block);
                }, 400);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void KnifeUse(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack itemMainHand = player.getInventory().getItemInMainHand();
        String heldID = OraxenItems.getIdByItem(player.getInventory().getItemInMainHand());

        ItemStack plant_fiber = OraxenItems.getItemById("plant_fiber").build();

        int chance = ThreadLocalRandom.current().nextInt(100) + 1; // random int from 1 to 100

        if (player.getGameMode() != GameMode.CREATIVE) {
            if (heldID == null) return;
            if (!heldID.contains("_knife")) return;
            reduceFlintKnifeDurability(itemMainHand);
            if (block.getType() == Material.GRASS || block.getType() == Material.TALL_GRASS) {
                if (chance <= 14) { // 14% chance
                    player.getWorld().dropItemNaturally(block.getRelative(player.getFacing().getOppositeFace()).getLocation(), plant_fiber);
                }
            }
        }
    }

    public static void reduceFlintKnifeDurability(ItemStack item) {
        ItemMeta var = item.getItemMeta();
        if (var instanceof Damageable) {
            Damageable toolDurabilityMeta = (Damageable) var;
            int durability = toolDurabilityMeta.getDamage();
            int maxDurability = item.getType().getMaxDurability();
            if (durability + 1 <= maxDurability) {
                toolDurabilityMeta.setDamage(durability + 1);
                item.setItemMeta(toolDurabilityMeta);
            }
            if (durability >= maxDurability) {
                item.setAmount(0);
            }
        }
    }
}
