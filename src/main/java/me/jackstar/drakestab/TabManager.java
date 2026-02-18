package me.jackstar.drakestab;

import me.jackstar.drakescraft.utils.MessageUtils;
import me.jackstar.drakescraft.utils.PlaceholderUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabManager implements Listener {

    private final JavaPlugin plugin;
    private BukkitTask task;
    private List<String> headerFrames = new ArrayList<>();
    private List<String> footerFrames = new ArrayList<>();
    private boolean sidebarEnabled;
    private int frameIndex;
    private int intervalTicks = 20;

    public TabManager(JavaPlugin plugin) {
        this.plugin = plugin;
        saveDefaultConfigFile();
        reload();
    }

    public void reload() {
        FileConfiguration config = loadConfig();
        headerFrames = config.getStringList("tab.header-frames");
        footerFrames = config.getStringList("tab.footer-frames");
        intervalTicks = Math.max(1, config.getInt("tab.update-interval-ticks", 20));
        sidebarEnabled = config.getBoolean("sidebar.enabled", true);
    }

    public void start() {
        stop();
        task = plugin.getServer().getScheduler().runTaskTimer(plugin, this::tick, 20L, intervalTicks);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    private void tick() {
        String headerFrame = headerFrames.isEmpty() ? "<gold>DrakesCore</gold>" : headerFrames.get(frameIndex % headerFrames.size());
        String footerFrame = footerFrames.isEmpty() ? "<gray>play.drakescraft.net</gray>" : footerFrames.get(frameIndex % footerFrames.size());

        for (Player player : Bukkit.getOnlinePlayers()) {
            String parsedHeader = PlaceholderUtils.applyPlaceholders(player, headerFrame);
            String parsedFooter = PlaceholderUtils.applyPlaceholders(player, footerFrame);
            Component header = MessageUtils.parse(parsedHeader);
            Component footer = MessageUtils.parse(parsedFooter);
            player.sendPlayerListHeaderAndFooter(header, footer);

            if (sidebarEnabled) {
                updateSidebar(player);
            }
        }
        frameIndex++;
    }

    private void updateSidebar(Player player) {
        org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) {
            return;
        }
        org.bukkit.scoreboard.Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard == null || scoreboard == manager.getMainScoreboard()) {
            scoreboard = manager.getNewScoreboard();
            player.setScoreboard(scoreboard);
        }

        org.bukkit.scoreboard.Objective objective = scoreboard.getObjective("drakestab");
        if (objective == null) {
            objective = scoreboard.registerNewObjective("drakestab", "dummy", "Drakes");
            objective.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);
        }
        objective.displayName(MessageUtils.parse("<gradient:gold:yellow><b>DrakesCore</b></gradient>"));

        clearSidebarEntries(scoreboard);

        double tps = Bukkit.getTPS()[0];
        setLine(scoreboard, objective, 6, "§6 ");
        setLine(scoreboard, objective, 5, "§fRank: §eN/A");
        setLine(scoreboard, objective, 4, "§fMoney: §aN/A");
        setLine(scoreboard, objective, 3, "§fPing: §b" + player.getPing());
        setLine(scoreboard, objective, 2, "§fTPS: §a" + String.format("%.2f", tps));
        setLine(scoreboard, objective, 1, "§7drakescraft.net");
    }

    private void clearSidebarEntries(org.bukkit.scoreboard.Scoreboard scoreboard) {
        for (String entry : new ArrayList<>(scoreboard.getEntries())) {
            scoreboard.resetScores(entry);
        }
    }

    private void setLine(org.bukkit.scoreboard.Scoreboard scoreboard, org.bukkit.scoreboard.Objective objective, int score, String entry) {
        objective.getScore(entry).setScore(score);
    }

    private FileConfiguration loadConfig() {
        return YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "tab.yml"));
    }

    private void saveDefaultConfigFile() {
        if (plugin.getResource("tab.yml") != null) {
            plugin.saveResource("tab.yml", false);
            return;
        }
        File file = new File(plugin.getDataFolder(), "tab.yml");
        if (file.exists()) {
            return;
        }
        try {
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }
            file.createNewFile();
        } catch (IOException ignored) {
        }
    }
}
