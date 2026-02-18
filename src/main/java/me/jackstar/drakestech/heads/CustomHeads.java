package me.jackstar.drakestech.heads;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CustomHeads {
    MACHINE_CORE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q0ZWM2YzkzZDY1MGI0NDQ5NDFjY2M5MzVjYWU3YmNhYzU5MTRhNGE4ZjQyODVhZTgyODRjYWM4MjQxNmM0In19fQ=="),
    ENERGY_CELL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmE4NGQ5NzgxNDRhNzhjYjU5NDI4Y2ViM2Q3ZDdmYjcxZTlhZDRjN2E2YjFjMDQ2YzIzMjM0YjgxZDcwYTk1ZCJ9fX0=");

    private static final Pattern URL_PATTERN = Pattern.compile("\"url\"\\s*:\\s*\"([^\"]+)\"");

    private final String base64;

    CustomHeads(String base64) {
        this.base64 = base64;
    }

    public ItemStack createItem() {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        if (meta == null) {
            return head;
        }

        URL skinUrl = decodeSkinUrl(base64);
        if (skinUrl == null) {
            return head;
        }

        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        PlayerTextures textures = profile.getTextures();
        textures.setSkin(skinUrl);
        profile.setTextures(textures);
        meta.setOwnerProfile(profile);
        head.setItemMeta(meta);
        return head;
    }

    private URL decodeSkinUrl(String base64Texture) {
        try {
            String decoded = new String(Base64.getDecoder().decode(base64Texture), StandardCharsets.UTF_8);
            Matcher matcher = URL_PATTERN.matcher(decoded);
            if (!matcher.find()) {
                return null;
            }
            String raw = matcher.group(1);
            return URI.create(raw).toURL();
        } catch (IllegalArgumentException | MalformedURLException ex) {
            return null;
        }
    }
}
