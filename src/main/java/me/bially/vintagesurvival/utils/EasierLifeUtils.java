package me.bially.vintagesurvival.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class EasierLifeUtils {

    public static void reduceItem(ItemStack item) {
        if (item.getAmount() == 1) {
            item.setAmount(0);
        } else {
            item.setAmount(item.getAmount() - 1);
        }
    }

    public static void reduceDurabilityCrafting(ItemStack item) {
        ItemMeta var = item.getItemMeta();
        if (var instanceof Damageable) {
            Damageable toolDurabilityMeta = (Damageable) var;
            int durability = toolDurabilityMeta.getDamage();
            int maxDurability = item.getType().getMaxDurability();
            if (durability + 1 <= maxDurability) {
                toolDurabilityMeta.setDamage(durability + 1);
                item.setItemMeta(toolDurabilityMeta);
                ItemStack newItem = new ItemStack(item);
            }
            if (durability >= maxDurability) {
                item.setAmount(0);
            }
        }
    }
}
