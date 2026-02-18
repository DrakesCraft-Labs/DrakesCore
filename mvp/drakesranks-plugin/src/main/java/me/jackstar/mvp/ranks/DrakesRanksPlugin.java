package me.jackstar.mvp.ranks;

import org.bukkit.plugin.java.JavaPlugin;

public class DrakesRanksPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DrakesRanks MVP enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DrakesRanks MVP disabled.");
    }
}
