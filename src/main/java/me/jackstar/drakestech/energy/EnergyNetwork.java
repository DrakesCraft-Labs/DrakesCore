package me.jackstar.drakestech.energy;

import java.util.Collection;
import java.util.Optional;

public interface EnergyNetwork {

    String getNetworkId();

    void addNode(EnergyNode node);

    void removeNode(String nodeId);

    Optional<EnergyNode> findNode(String nodeId);

    Collection<EnergyNode> getNodes();

    int getStoredEnergy(String nodeId);

    int offerEnergy(String nodeId, int amount);

    int extractEnergy(String nodeId, int amount);

    void tickFlow();
}
