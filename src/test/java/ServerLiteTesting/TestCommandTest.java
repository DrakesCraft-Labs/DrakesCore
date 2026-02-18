package ServerLiteTesting;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import me.jackstar.drakescraft.DrakesCore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCommandTest {

    private ServerMock server;
    private DrakesCore plugin;

    @BeforeEach
    public void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(DrakesCore.class);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void testDrakesTestCommand() {
        // Create a fake player
        PlayerMock player = server.addPlayer("JackStar");

        // Run the command
        boolean result = player.performCommand("drakestest");

        // Verify command execution
        assertTrue(result, "Command should return true");

        // Check if player received the item
        ItemStack item = player.getInventory().getItemInMainHand();
        assertEquals(Material.NETHERITE_PICKAXE, item.getType(), "Player should have a Netherite Pickaxe");

        // Log for the user to see
        server.getLogger().info("Simulation: Player 'JackStar' ran /drakestest and received " + item.getType());
    }
}
