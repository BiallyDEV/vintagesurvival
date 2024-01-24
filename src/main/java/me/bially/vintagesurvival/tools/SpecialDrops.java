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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static me.bially.vintagesurvival.tools.ToolsDurability.reduceFlintToolDurability;

public class SpecialDrops implements Listener {

    public JavaPlugin plugin = VintageSurvival.getPlugin(VintageSurvival.class);

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack itemMainHand = player.getInventory().getItemInMainHand();
        String heldID = OraxenItems.getIdByItem(player.getInventory().getItemInMainHand());

        ItemStack pebble = OraxenItems.getItemById("pebble_item").build();
        pebble.setAmount(2);

        if (player.getGameMode() != GameMode.CREATIVE) {
            if (heldID == null) return;
            if (heldID.equals("flint_pickaxe")) {
                if (block.getType() == Material.STONE || block.getType() == Material.COBBLESTONE ||
                        block.getType() == Material.INFESTED_COBBLESTONE || block.getType() == Material.INFESTED_STONE) {
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    player.getWorld().dropItemNaturally(block.getRelative(player.getFacing().getOppositeFace()).getLocation(), pebble);
                    reduceFlintToolDurability(itemMainHand);
                }
            }
        }
    }
}
