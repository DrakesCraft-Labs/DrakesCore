package ServerLiteTesting;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.jackstar.drakescraft.DrakesCore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrakesCoreTest {

    private ServerMock server;
    private DrakesCore plugin;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load our plugin
        plugin = MockBukkit.load(DrakesCore.class);
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testPluginStartup() {
        // Verify plugin is loaded
        assertNotNull(plugin);
        assertTrue(plugin.isEnabled());

        // Use the logger to show it works
        server.getLogger().info("Simulation: Plugin loaded successfully!");
    }
}
