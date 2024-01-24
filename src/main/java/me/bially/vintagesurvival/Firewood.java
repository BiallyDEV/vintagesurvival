package me.bially.vintagesurvival;

import io.th0rgal.oraxen.api.OraxenBlocks;
import io.th0rgal.oraxen.api.OraxenFurniture;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.mechanics.provided.gameplay.furniture.FurnitureMechanic;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Firewood implements Listener {

    public JavaPlugin plugin = VintageSurvival.getPlugin(VintageSurvival.class);

    private final List<Block> blockCooldownList = new ArrayList<>();
    public NamespacedKey usesKey = new NamespacedKey(plugin, "bottleUses");

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void FirewoodPlace(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        String heldID = OraxenItems.getIdByItem(player.getInventory().getItemInMainHand());
        Block targetBlock = player.getTargetBlock(null, 100);
        Block targetBlockLocation = targetBlock.getRelative(event.getBlockFace());
        Block blockUnder = targetBlockLocation.getRelative(BlockFace.DOWN);

        ArrayList<String> isFirewoodPile = new ArrayList<String>();
        isFirewoodPile.add("firewood_pile_1");
        isFirewoodPile.add("firewood_pile_2");
        isFirewoodPile.add("firewood_pile_3");
        isFirewoodPile.add("firewood_pile_4");
        isFirewoodPile.add("firewood_pile_5");
        isFirewoodPile.add("firewood_pile_6");
        isFirewoodPile.add("firewood_pile_7");
        isFirewoodPile.add("firewood_pile_8");
        isFirewoodPile.add("firewood_pile_9");
        isFirewoodPile.add("firewood_pile_10");
        isFirewoodPile.add("firewood_pile_11");
        isFirewoodPile.add("firewood_pile_12");
        isFirewoodPile.add("firewood_pile_13");
        isFirewoodPile.add("firewood_pile_14");
        isFirewoodPile.add("firewood_pile_15");
        isFirewoodPile.add("firewood_pile_16");

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getHand() != EquipmentSlot.HAND) return;
        if (event.getPlayer().isSneaking()) return;
        if (heldID == null) return;
        if (!heldID.equals("firewood")) return;
        if (blockUnder.getType().equals(Material.BARRIER) || blockUnder.getType().equals(Material.TRIPWIRE)
                || blockUnder.getType().equals(Material.AIR) || !blockUnder.isSolid()) return;
        if (targetBlockLocation.isSolid()) return;
        if (targetBlock.getType().equals(Material.BARRIER)) {
            FurnitureMechanic furnitureMechanic = OraxenFurniture.getFurnitureMechanic(targetBlock);
            if (isFirewoodPile.contains(furnitureMechanic.getItemID())) return;
        }

        OraxenFurniture.place(targetBlockLocation.getLocation(), "firewood_pile_1", yawToRotation(player.getLocation().getYaw()), BlockFace.UP);
        event.getPlayer().swingMainHand();
        if (player.getGameMode() != GameMode.CREATIVE) {
            reduceItem(item);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void FirewoodPlaceMulti(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        String heldID = OraxenItems.getIdByItem(player.getInventory().getItemInMainHand());
        Block targetBlock = player.getTargetBlock(null, 100);
        Block targetBlockLocation = targetBlock.getRelative(event.getBlockFace());
        Block blockUnder = targetBlockLocation.getRelative(BlockFace.DOWN);
        ItemStack firewood = OraxenItems.getItemById("firewood").build();

        ArrayList<String> isFirewoodPile = new ArrayList<String>();
        isFirewoodPile.add("firewood_pile_1");
        isFirewoodPile.add("firewood_pile_2");
        isFirewoodPile.add("firewood_pile_3");
        isFirewoodPile.add("firewood_pile_4");
        isFirewoodPile.add("firewood_pile_5");
        isFirewoodPile.add("firewood_pile_6");
        isFirewoodPile.add("firewood_pile_7");
        isFirewoodPile.add("firewood_pile_8");
        isFirewoodPile.add("firewood_pile_9");
        isFirewoodPile.add("firewood_pile_10");
        isFirewoodPile.add("firewood_pile_11");
        isFirewoodPile.add("firewood_pile_12");
        isFirewoodPile.add("firewood_pile_13");
        isFirewoodPile.add("firewood_pile_14");
        isFirewoodPile.add("firewood_pile_15");
        isFirewoodPile.add("firewood_pile_16");

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getHand() != EquipmentSlot.HAND) return;
        if (!event.getPlayer().isSneaking()) return;
        if (heldID == null) return;
        if (!heldID.equals("firewood")) return;
        if (!player.getInventory().containsAtLeast(firewood, 4)) return;
        if (blockUnder.getType().equals(Material.BARRIER) || blockUnder.getType().equals(Material.TRIPWIRE)
                || blockUnder.getType().equals(Material.AIR) || !blockUnder.isSolid()) return;
        if (targetBlockLocation.isSolid()) return;
        if (targetBlock.getType().equals(Material.BARRIER)) {
            FurnitureMechanic furnitureMechanic = OraxenFurniture.getFurnitureMechanic(targetBlock);
            if (isFirewoodPile.contains(furnitureMechanic.getItemID())) return;
        }

        OraxenFurniture.place(targetBlockLocation.getLocation(), "firewood_pile_4", yawToRotation(player.getLocation().getYaw()), BlockFace.UP);
        event.getPlayer().swingMainHand();
        if (player.getGameMode() != GameMode.CREATIVE) {
            reduceItemx4(item);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void FirewoodAdd(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        String heldID = OraxenItems.getIdByItem(player.getInventory().getItemInMainHand());
        Block block = event.getClickedBlock();

        ArrayList<String> isFirewoodPile = new ArrayList<String>();
        isFirewoodPile.add("firewood_pile_1");
        isFirewoodPile.add("firewood_pile_2");
        isFirewoodPile.add("firewood_pile_3");
        isFirewoodPile.add("firewood_pile_4");
        isFirewoodPile.add("firewood_pile_5");
        isFirewoodPile.add("firewood_pile_6");
        isFirewoodPile.add("firewood_pile_7");
        isFirewoodPile.add("firewood_pile_8");
        isFirewoodPile.add("firewood_pile_9");
        isFirewoodPile.add("firewood_pile_10");
        isFirewoodPile.add("firewood_pile_11");
        isFirewoodPile.add("firewood_pile_12");
        isFirewoodPile.add("firewood_pile_13");
        isFirewoodPile.add("firewood_pile_14");
        isFirewoodPile.add("firewood_pile_15");

        if (block == null || event.getHand() != EquipmentSlot.HAND)
            return;
        if (heldID == null) return;
        if (!heldID.equals("firewood")) return;
        if (event.getPlayer().isSneaking()) return;
        if (block.getType() == Material.BARRIER) {
            FurnitureMechanic furnitureMechanic = OraxenFurniture.getFurnitureMechanic(block);
            if (furnitureMechanic == null) return;
            Entity baseEntity = furnitureMechanic.getBaseEntity(block);
            if (!isFirewoodPile.contains(furnitureMechanic.getItemID())) return;

            if (player.getGameMode() != GameMode.CREATIVE) {
                reduceItem(item);
            }
            event.getPlayer().swingMainHand();
            switch (OraxenFurniture.getFurnitureMechanic(block).getItemID()) {
                case "firewood_pile_1" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_2", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_2" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_3", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_3" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_4", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_4" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_5", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_5" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_6", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_6" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_7", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_7" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_8", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_8" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_9", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_9" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_10", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_10" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_11", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_11" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_12", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_12" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_13", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_13" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_14", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_14" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_15", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_15" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_16", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void FirewoodAddMulti(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        String heldID = OraxenItems.getIdByItem(player.getInventory().getItemInMainHand());
        Block block = event.getClickedBlock();
        ItemStack firewood = OraxenItems.getItemById("firewood").build();

        ArrayList<String> isFirewoodPile = new ArrayList<String>();
        isFirewoodPile.add("firewood_pile_1");
        isFirewoodPile.add("firewood_pile_2");
        isFirewoodPile.add("firewood_pile_3");
        isFirewoodPile.add("firewood_pile_4");
        isFirewoodPile.add("firewood_pile_5");
        isFirewoodPile.add("firewood_pile_6");
        isFirewoodPile.add("firewood_pile_7");
        isFirewoodPile.add("firewood_pile_8");
        isFirewoodPile.add("firewood_pile_9");
        isFirewoodPile.add("firewood_pile_10");
        isFirewoodPile.add("firewood_pile_11");
        isFirewoodPile.add("firewood_pile_12");

        if (block == null || event.getHand() != EquipmentSlot.HAND)
            return;
        if (heldID == null) return;
        if (!heldID.equals("firewood")) return;
        if (!player.getInventory().containsAtLeast(firewood, 4)) return;
        if (!event.getPlayer().isSneaking()) return;
        if (block.getType() == Material.BARRIER) {
            FurnitureMechanic furnitureMechanic = OraxenFurniture.getFurnitureMechanic(block);
            if (furnitureMechanic == null) return;
            Entity baseEntity = furnitureMechanic.getBaseEntity(block);
            if (!isFirewoodPile.contains(furnitureMechanic.getItemID())) return;

            if (player.getGameMode() != GameMode.CREATIVE) {
                reduceItemx4(item);
            }
            event.getPlayer().swingMainHand();
            switch (OraxenFurniture.getFurnitureMechanic(block).getItemID()) {
                case "firewood_pile_1" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_5", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_2" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_6", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_3" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_7", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_4" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_8", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_5" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_9", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_6" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_10", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_7" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_11", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_8" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_12", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_9" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_13", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_10" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_14", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_11" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_15", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_12" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_16", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void FirewoodTake(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        ItemStack firewood = OraxenItems.getItemById("firewood").build();

        ArrayList<String> isFirewoodPile = new ArrayList<String>();
        isFirewoodPile.add("firewood_pile_1");
        isFirewoodPile.add("firewood_pile_2");
        isFirewoodPile.add("firewood_pile_3");
        isFirewoodPile.add("firewood_pile_4");
        isFirewoodPile.add("firewood_pile_5");
        isFirewoodPile.add("firewood_pile_6");
        isFirewoodPile.add("firewood_pile_7");
        isFirewoodPile.add("firewood_pile_8");
        isFirewoodPile.add("firewood_pile_9");
        isFirewoodPile.add("firewood_pile_10");
        isFirewoodPile.add("firewood_pile_11");
        isFirewoodPile.add("firewood_pile_12");
        isFirewoodPile.add("firewood_pile_13");
        isFirewoodPile.add("firewood_pile_14");
        isFirewoodPile.add("firewood_pile_15");
        isFirewoodPile.add("firewood_pile_16");

        if (block == null || event.getHand() != EquipmentSlot.HAND || !(event.getAction() == Action.RIGHT_CLICK_BLOCK))
            return;
        if (item.getType() != Material.AIR) return;
        if (event.getPlayer().isSneaking()) return;
        if (block.getType() == Material.BARRIER) {
            FurnitureMechanic furnitureMechanic = OraxenFurniture.getFurnitureMechanic(block);
            if (furnitureMechanic == null) return;
            Entity baseEntity = furnitureMechanic.getBaseEntity(block);
            if (!isFirewoodPile.contains(furnitureMechanic.getItemID())) return;

            if (player.getGameMode() != GameMode.CREATIVE)
                player.getInventory().addItem(firewood);
            event.getPlayer().swingMainHand();
            switch (OraxenFurniture.getFurnitureMechanic(block).getItemID()) {
                case "firewood_pile_16" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_15", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_15" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_14", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_14" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_13", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_13" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_12", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_12" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_11", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_11" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_10", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_10" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_9", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_9" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_8", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_8" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_7", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_7" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_6", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_6" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_5", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_5" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_4", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_4" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_3", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_3" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_2", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_2" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_1", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_1" -> {
                    OraxenFurniture.remove(baseEntity, null);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void FirewoodTakeMulti(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        ItemStack firewood = OraxenItems.getItemById("firewood").build();
        firewood.setAmount(4);

        ArrayList<String> isFirewoodPile = new ArrayList<String>();
        isFirewoodPile.add("firewood_pile_4");
        isFirewoodPile.add("firewood_pile_5");
        isFirewoodPile.add("firewood_pile_6");
        isFirewoodPile.add("firewood_pile_7");
        isFirewoodPile.add("firewood_pile_8");
        isFirewoodPile.add("firewood_pile_9");
        isFirewoodPile.add("firewood_pile_10");
        isFirewoodPile.add("firewood_pile_11");
        isFirewoodPile.add("firewood_pile_12");
        isFirewoodPile.add("firewood_pile_13");
        isFirewoodPile.add("firewood_pile_14");
        isFirewoodPile.add("firewood_pile_15");
        isFirewoodPile.add("firewood_pile_16");

        if (block == null || event.getHand() != EquipmentSlot.HAND || !(event.getAction() == Action.RIGHT_CLICK_BLOCK))
            return;
        if (item.getType() != Material.AIR) return;
        if (!event.getPlayer().isSneaking()) return;
        if (block.getType() == Material.BARRIER) {
            FurnitureMechanic furnitureMechanic = OraxenFurniture.getFurnitureMechanic(block);
            if (furnitureMechanic == null) return;
            Entity baseEntity = furnitureMechanic.getBaseEntity(block);
            if (!isFirewoodPile.contains(furnitureMechanic.getItemID())) return;

            if (player.getGameMode() != GameMode.CREATIVE)
                player.getInventory().addItem(firewood);
            event.getPlayer().swingMainHand();
            switch (OraxenFurniture.getFurnitureMechanic(block).getItemID()) {
                case "firewood_pile_16" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_12", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_15" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_11", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_14" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_10", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_13" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_9", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_12" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_8", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_11" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_7", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_10" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_6", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_9" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_5", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_8" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_4", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_7" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_3", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_6" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_2", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_5" -> {
                    OraxenFurniture.remove(baseEntity, null);
                    OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_1", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                }
                case "firewood_pile_4" -> {
                    OraxenFurniture.remove(baseEntity, null);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void OnFirewoodIgnite(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (block == null || event.getHand() != EquipmentSlot.HAND || !(event.getAction() == Action.RIGHT_CLICK_BLOCK))
            return;

        Location location = block.getLocation();
        World world = location.getWorld();
        int x = location.getBlockX();
        int z = location.getBlockZ();
        int y = location.getBlockY();

        Block b1 = new Location(world, x + 1, y, z).getBlock();
        Block b2 = new Location(world, x - 1, y, z).getBlock();
        Block b3 = new Location(world, x, y, z + 1).getBlock();
        Block b4 = new Location(world, x, y, z - 1).getBlock();
        Block b5 = new Location(world, x , y + 1, z).getBlock();
        Block b6 = new Location(world, x , y - 1, z).getBlock();

        if (item.getType() != Material.FLINT_AND_STEEL) return;
        if (block.getType() == Material.BARRIER) {
            FurnitureMechanic furnitureMechanic = OraxenFurniture.getFurnitureMechanic(block);
            if (furnitureMechanic == null) return;
            Entity baseEntity = furnitureMechanic.getBaseEntity(block);
            if (furnitureMechanic.getItemID().equals("firewood_pile_16")) {
                OraxenFurniture.remove(baseEntity, null);
                OraxenFurniture.place(baseEntity.getLocation(), "firewood_pile_ignited", yawToRotation(FurnitureMechanic.getFurnitureYaw(baseEntity)), baseEntity.getFacing());
                event.getPlayer().swingMainHand();
                blockCooldownList.add(block);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    if (b1.isSolid() && b2.isSolid() && b3.isSolid() && b4.isSolid() && b5.isSolid() && b6.isSolid()) {
                        OraxenFurniture.remove(location, null);
                        OraxenBlocks.place("charcoal_block", location);
                        blockCooldownList.remove(block);
                    }
                    if (!b1.isSolid() || !b2.isSolid() || !b3.isSolid() || !b4.isSolid() || !b5.isSolid() || !b6.isSolid()) {
                        OraxenFurniture.remove(location, null);
                        OraxenBlocks.place("ash_block", location);
                        blockCooldownList.remove(block);
                    }
                }, 400L);
            }
        }
    }

    public static void reduceItem(ItemStack item) {
        if (item.getAmount() == 1) {
            item.setAmount(0);
        } else {
            item.setAmount(item.getAmount() - 1);
        }
    }

    public static void reduceItemx4(ItemStack item) {
        if (item.getAmount() == 4) {
            item.setAmount(0);
        } else {
            item.setAmount(item.getAmount() - 4);
        }
    }

    public static Rotation yawToRotation(float yaw) {
        return Rotation.values()[Math.round(yaw / 45f) & 0x7];
    }
}
