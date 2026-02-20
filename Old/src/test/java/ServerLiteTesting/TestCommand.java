package me.jackstar.drakescraft.commands;

import me.jackstar.drakescraft.utils.ItemBuilder;
import me.jackstar.drakescraft.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            MessageUtils.send(sender, "<red>Only players can use this command!");
            return true;
        }

        // Test MessageUtils
        MessageUtils.send(player, "<gradient:gold:yellow><b>Testing DrakesCore Utilities!</b></gradient>");
        MessageUtils.send(player, "&7(This is a legacy color code test)");

        // Test ItemBuilder
        ItemStack godTools = new ItemBuilder(Material.NETHERITE_PICKAXE)
                .name("<rainbow>Drake's Pickaxe")
                .lore("<gray>Use this to break bedrock!", "", "<blue>Rarity: <gold>LEGENDARY")
                .glowing()
                .build();

        player.getInventory().addItem(godTools);
        MessageUtils.send(player, "<green>You received the reliable tool!");

        return true;
    }
}
