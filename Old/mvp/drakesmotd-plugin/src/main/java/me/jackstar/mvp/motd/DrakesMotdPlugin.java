package me.jackstar.mvp.motd;

import org.bukkit.plugin.java.JavaPlugin;

public class DrakesMotdPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DrakesMotd MVP enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DrakesMotd MVP disabled.");
    }
}
