package me.jackstar.drakestech.multiblock;

import me.jackstar.drakestech.machines.Rotation;
import org.bukkit.Location;

public interface MultiblockDetector {

    String getPatternId();

    MultiblockValidationResult validate(Location origin, Rotation rotation);
}
