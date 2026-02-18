package me.jackstar.mvp.tab;

import org.bukkit.plugin.java.JavaPlugin;

public class DrakesTabPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DrakesTab MVP enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DrakesTab MVP disabled.");
    }
}
