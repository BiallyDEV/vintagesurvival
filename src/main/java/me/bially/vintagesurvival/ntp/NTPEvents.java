package me.bially.vintagesurvival.ntp;

import io.th0rgal.oraxen.api.OraxenItems;
import me.bially.vintagesurvival.VintageSurvival;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

import static me.bially.vintagesurvival.utils.EasierLifeUtils.*;

public class NTPEvents implements Listener {
    private final VintageSurvival plugin;

    public NTPEvents(VintageSurvival plugin) {
        this.plugin = plugin;
    }

    final TextComponent NTPText = Component.text("Do tego bloku bedziesz potrzebowac siekiery")
            .color(TextColor.color(0xE21719));
    final TextComponent NTSText = Component.text("Do tego bloku bedziesz potrzebowac kilofa")
            .color(TextColor.color(0xE21719));

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemMainHand = player.getInventory().getItemInMainHand();
        Block block = event.getBlock();

        if (player.getGameMode() != GameMode.CREATIVE) {
            if (block.getType().toString().contains("LOG") || block.getType().toString().contains("PLANKS")
                    || block.getType().toString().contains("WOOD")) {
                if (!itemMainHand.getType().toString().contains("_AXE")) {
                    event.setCancelled(true);
                    new BukkitRunnable() {
                        private int i = 0;

                        @Override
                        public void run() {
                            if (i >= 20) {
                                cancel();
                            }
                            ++i;
                            player.sendActionBar(NTPText);
                        }
                    }.runTaskTimer(plugin, 2L, 0L);
                }
            }

            if (block.getType().toString().contains("STONE") || block.getType().toString().contains("_ORE")) {
                if (!itemMainHand.getType().toString().contains("_PICKAXE")) {
                    event.setCancelled(true);
                    new BukkitRunnable() {
                        private int i = 0;

                        @Override
                        public void run() {
                            if (i >= 20) {
                                cancel();
                            }
                            ++i;
                            player.sendActionBar(NTSText);
                        }
                    }.runTaskTimer(plugin, 2L, 0L);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void Knapping(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemMainHand = player.getInventory().getItemInMainHand();
        Block block = event.getClickedBlock();
        ItemStack flint_shard = OraxenItems.getItemById("flint_shard").build();

        int chance = ThreadLocalRandom.current().nextInt(100) + 1; // random int from 1 to 100

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getHand() != EquipmentSlot.HAND) return;
        if (!itemMainHand.getType().equals(Material.FLINT) || block == null) return;
        if (block.getType() != Material.STONE || block.getType() != Material.GRANITE
                || block.getType() != Material.ANDESITE || block.getType() != Material.DIORITE
                || block.getType() != Material.TUFF || block.getType() != Material.CALCITE) {
            if (player.getGameMode() != GameMode.CREATIVE) {
                reduceItem(itemMainHand);
                event.getPlayer().swingMainHand();
                player.playSound(player.getLocation(), "knapping", 1.0F, 1.0F);
                if (chance <= 18) { // 18% chance
                    player.getWorld().dropItemNaturally(block.getRelative(player.getFacing().getOppositeFace()).getLocation(), flint_shard);
                }
            }
        }
    }
}
