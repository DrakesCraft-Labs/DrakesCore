package me.jackstar.mvp.core;

import org.bukkit.plugin.java.JavaPlugin;

public class DrakesCorePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DrakesCore MVP enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DrakesCore MVP disabled.");
    }
}
