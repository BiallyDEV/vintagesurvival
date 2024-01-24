package me.bially.vintagesurvival.commands;

import me.bially.vintagesurvival.VintageSurvival;
import me.bially.vintagesurvival.nutrition.NutritionData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VSCommands implements CommandExecutor {
    private final VintageSurvival plugin;

    public VSCommands(VintageSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        // /makro
        if (cmd.getName().equalsIgnoreCase("makro")) {
            if (sender.hasPermission("vs.makro")) {
                NutritionData data = plugin.nutritionManager.getNutrition(player.getUniqueId());
                final TextComponent nutrition = Component.text("----[")
                        .color(NamedTextColor.GRAY)
                        .append(Component.text("Makro", NamedTextColor.AQUA))
                        .append(Component.text("]----", NamedTextColor.GRAY));
                player.sendMessage(nutrition);
                final TextComponent carbs = Component.text("Weglowodany:")
                        .color(NamedTextColor.GOLD)
                        .append(Component.text(data.getCarbs(), NamedTextColor.GREEN));
                player.sendMessage(carbs);
                final TextComponent protein = Component.text("Bialko:")
                        .color(NamedTextColor.GOLD)
                        .append(Component.text(data.getProtein(), NamedTextColor.GREEN));
                player.sendMessage(protein);
                final TextComponent fat = Component.text("Tluszcz:")
                        .color(NamedTextColor.GOLD)
                        .append(Component.text(data.getFat(), NamedTextColor.GREEN));
                player.sendMessage(fat);
                final TextComponent vitamins = Component.text("Witaminy:")
                        .color(NamedTextColor.GOLD)
                        .append(Component.text(data.getVitamins(), NamedTextColor.GREEN));
                player.sendMessage(vitamins);
            }
        }

        // /craftcrafting
        if (cmd.getName().equalsIgnoreCase("craftcrafting")) {
            player.sendMessage("lalal");
        }
        return true;
    }
}
