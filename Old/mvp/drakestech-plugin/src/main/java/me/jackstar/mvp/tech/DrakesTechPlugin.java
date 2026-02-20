package me.jackstar.mvp.tech;

import org.bukkit.plugin.java.JavaPlugin;

public class DrakesTechPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DrakesTech MVP enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DrakesTech MVP disabled.");
    }
}
