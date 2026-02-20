package me.jackstar.mvp.crates;

import org.bukkit.plugin.java.JavaPlugin;

public class DrakesCratesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DrakesCrates MVP enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DrakesCrates MVP disabled.");
    }
}
