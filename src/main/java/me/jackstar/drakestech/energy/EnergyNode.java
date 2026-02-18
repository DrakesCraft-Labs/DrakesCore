package me.jackstar.drakestech.energy;

import org.bukkit.Location;

public record EnergyNode(String id, Location location, int capacity, int maxInputPerTick, int maxOutputPerTick) {
}
