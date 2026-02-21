package me.jackstar.drakescraft.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public final class DrShortcutCommand implements TabExecutor {

    private final Map<String, String> routes = new LinkedHashMap<>();

    public DrShortcutCommand() {
        // Canonical routes
        routes.put("tab", "drakestab");
        routes.put("tech", "drakestech");
        routes.put("ranks", "rank");
        routes.put("rank", "rank");
        routes.put("crates", "drakescrates");
        routes.put("motd", "drakesmotd");
        routes.put("worlds", "drakesworlds");
        routes.put("world", "drakesworlds");

        // Short aliases
        routes.put("t", "drakestab");
        routes.put("te", "drakestech");
        routes.put("r", "rank");
        routes.put("c", "drakescrates");
        routes.put("m", "drakesmotd");
        routes.put("w", "drakesworlds");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String[] args) {
        if (args.length == 0) {
            sendHelp(sender, label);
            return true;
        }

        String route = routes.get(args[0].toLowerCase(Locale.ROOT));
        if (route == null) {
            sendHelp(sender, label);
            return true;
        }

        String tail = args.length <= 1 ? "" : String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        String toDispatch = tail.isBlank() ? route : route + " " + tail;
        boolean ok = sender.getServer().dispatchCommand(sender, toDispatch);
        if (!ok) {
            sender.sendMessage(Component.text("No se pudo ejecutar: /" + toDispatch, NamedTextColor.RED));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias,
            @NotNull String[] args) {
        if (args.length == 1) {
            String prefix = args[0].toLowerCase(Locale.ROOT);
            return routes.keySet().stream()
                    .distinct()
                    .filter(key -> key.startsWith(prefix))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    private void sendHelp(CommandSender sender, String label) {
        sender.sendMessage(Component.text("Atajos Drakes:", NamedTextColor.GOLD));
        sender.sendMessage(Component.text("/" + label + " tab <...>  -> /drakestab", NamedTextColor.YELLOW));
        sender.sendMessage(Component.text("/" + label + " tech <...> -> /drakestech", NamedTextColor.YELLOW));
        sender.sendMessage(Component.text("/" + label + " ranks <...> -> /rank", NamedTextColor.YELLOW));
        sender.sendMessage(Component.text("/" + label + " crates <...> -> /drakescrates", NamedTextColor.YELLOW));
        sender.sendMessage(Component.text("/" + label + " motd <...> -> /drakesmotd", NamedTextColor.YELLOW));
        sender.sendMessage(Component.text("/" + label + " worlds <...> -> /drakesworlds", NamedTextColor.YELLOW));
    }
}
