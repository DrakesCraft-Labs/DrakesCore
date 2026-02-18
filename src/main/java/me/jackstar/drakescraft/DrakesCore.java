package me.jackstar.drakescraft;

import me.jackstar.drakescrates.application.repositories.CrateRepository;
import me.jackstar.drakescrates.application.usecases.OpenCrateUseCase;
import me.jackstar.drakescrates.infrastructure.persistence.yaml.YamlCrateRepository;
import me.jackstar.drakescrates.presentation.animation.RouletteAnimation;
import me.jackstar.drakescrates.presentation.commands.DrakesCratesCommand;
import me.jackstar.drakescrates.presentation.listeners.CrateListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import me.jackstar.drakescraft.api.DrakesCoreAPI;

public class DrakesCore extends JavaPlugin implements DrakesCoreAPI {

    private static DrakesCore instance;
    private CrateRepository crateRepository;
    private RouletteAnimation rouletteAnimation;

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
        if (getResource("config.yml") != null) {
            saveDefaultConfig();
        }
        if (getResource("crates.yml") != null) {
            saveResource("crates.yml", false);
        }

        // Plugin startup logic
        getComponentLogger().info(Component.text("----------------------------------------", NamedTextColor.GOLD));
        getComponentLogger().info(Component.text(" DrakesCore has been ENABLED! ", NamedTextColor.AQUA));
        getComponentLogger().info(Component.text(" Prepare for an epic adventure! ", NamedTextColor.LIGHT_PURPLE));
        getComponentLogger().info(Component.text("----------------------------------------", NamedTextColor.GOLD));

        crateRepository = new YamlCrateRepository(this);
        OpenCrateUseCase openCrateUseCase = new OpenCrateUseCase();
        rouletteAnimation = new RouletteAnimation(this);

        PluginCommand drakesCratesCommand = getCommand("drakescrates");
        if (drakesCratesCommand != null) {
            drakesCratesCommand.setExecutor(new DrakesCratesCommand(crateRepository));
        } else {
            getLogger().warning("Command 'drakescrates' not found in plugin.yml.");
        }

        getServer().getPluginManager().registerEvents(
                new CrateListener(crateRepository, openCrateUseCase, rouletteAnimation),
                this);
    }

    @Override
    public void onDisable() {
        if (rouletteAnimation != null) {
            rouletteAnimation.shutdown();
        }
        // Plugin shutdown logic
        getComponentLogger().info(Component.text("DrakesCore has been disabled.", NamedTextColor.RED));
        instance = null;
    }
}
