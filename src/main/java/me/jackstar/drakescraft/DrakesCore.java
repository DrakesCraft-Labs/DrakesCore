package me.jackstar.drakescraft;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.jackstar.drakescraft.api.DrakesCoreAPI;

public class DrakesCore extends JavaPlugin implements DrakesCoreAPI {

    private static DrakesCore instance;

    public static DrakesCore getInstance() {
        return instance;
    }

    @Override
    public JavaPlugin getPlugin() {
        return this;
    }

    @Override
    public void onEnable() {
        instance = this;

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
        instance = null;
    }
}
