package me.jackstar.drakestech.machines;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

public abstract class AbstractMachine {

    private final UUID machineId;
    private Location origin;
    private Rotation rotation;
    private final Inventory inventory;

    protected AbstractMachine(UUID machineId, Location origin, Rotation rotation, int inventorySize, String inventoryTitle) {
        this.machineId = Objects.requireNonNull(machineId, "machineId");
        this.origin = Objects.requireNonNull(origin, "origin").clone();
        this.rotation = Objects.requireNonNull(rotation, "rotation");
        this.inventory = Bukkit.createInventory(null, inventorySize, inventoryTitle);
    }

    public UUID getMachineId() {
        return machineId;
    }

    public Location getOrigin() {
        return origin.clone();
    }

    public void setOrigin(Location origin) {
        this.origin = Objects.requireNonNull(origin, "origin").clone();
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = Objects.requireNonNull(rotation, "rotation");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void clearInventory() {
        inventory.clear();
    }

    public void openInventory(Player player) {
        if (player != null) {
            player.openInventory(inventory);
        }
    }

    public boolean canAccept(ItemStack stack) {
        return true;
    }

    public abstract String getMachineTypeId();

    public abstract void tick();

    public abstract boolean isStructureValid();

    public void onInteract(Player player) {
        openInventory(player);
    }

    public void onPlace() {
    }

    public void onBreak() {
    }
}
