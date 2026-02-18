package me.jackstar.drakescraft;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class DrakesCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getComponentLogger().info(Component.text("----------------------------------------", NamedTextColor.GOLD));
        getComponentLogger().info(Component.text(" DrakesCore has been ENABLED! ", NamedTextColor.AQUA));
        getComponentLogger().info(Component.text(" Prepare for an epic adventure! ", NamedTextColor.LIGHT_PURPLE));
        getComponentLogger().info(Component.text("----------------------------------------", NamedTextColor.GOLD));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getComponentLogger().info(Component.text("DrakesCore has been disabled.", NamedTextColor.RED));
    }
}
