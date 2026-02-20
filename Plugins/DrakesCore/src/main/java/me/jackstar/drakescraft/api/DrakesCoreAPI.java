package me.jackstar.drakescraft.api;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Public API for DrakesCore.
 * Addons should use this interface to interact with the core plugin.
 */
public interface DrakesCoreAPI {

    /**
     * Gets the main plugin instance.
     * 
     * @return The JavaPlugin instance.
     */
    JavaPlugin getPlugin();

    // Future API methods will be added here
    // e.g., getDatabaseManager(), getConfigManager(), etc.
}
