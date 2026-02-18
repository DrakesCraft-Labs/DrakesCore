# DrakesCore - The Foundation of DrakesCraft

Welcome to **DrakesCore**, the central nervous system for the DrakesCraft network.

This repository serves as the **Core API** and foundational plugin for all future server functionalities. It is designed to be modular, high-performance, and easy to extend.

## 🏗️ Project Architecture

DrakesCore is built using **Java 21** and targeted for **Paper/Purpur 1.20.6**.

### Key Components (Current):
1.  **Singleton Pattern (`DrakesCore.getInstance()`)**:
    -   Provides global access to the main plugin instance.
    -   Ensures only one instance of the core logic exists.
2.  **MessageUtils (`me.jackstar.drakescraft.utils.MessageUtils`)**:
    -   **Modern**: Full support for Adventure API & MiniMessage (e.g., `<gradient:red:blue>`).
    -   **Legacy**: Backward compatibility for `&a` color codes.
    -   **Centralized**: All message parsing happens here.
3.  **ItemBuilder (`me.jackstar.drakescraft.utils.ItemBuilder`)**:
    -   Fluent API for creating intricate `ItemStack`s.
    -   Supports: Display names (MiniMessage), Lore, Enchantments, Flags, Custom Model Data.
4.  **Testing (`src/test/java/ServerLiteTesting`)**:
    -   **MockBukkit Integration**: Simulates a running server environment for unit tests.
    -   Run tests via `mvn test`. No need to launch the full game client.

## 🚀 Future Roadmap & Possibilities

DrakesCore is just the beginning. The vision is to build a complete ecosystem around it.

### Planned Core Modules
-   [ ] **Database Manager**: Connection pooling (HikariCP) for MySQL/SQLite.
-   [ ] **Config Manager**: Annotation-based configuration system for easy YAML handling.
-   [ ] **Permission Wrapper**: Simplified API for checking player ranks and permissions.

### Future Addons (The "Drake" Suite)
These will be separate plugins or modules depending on DrakesCore:

#### 1. 📦 DrakesCrates (Crate System)
-   **Concept**: A highly customizable crate system similar to CrazyCrates/PhoenixCrates.
-   **Features**:
    -   Physical and Virtual Keys.
    -   Customizable opening animations (CS:GO style, Roulette, Cosmic).
    -   Reward preview menus.
    -   Integration with `DrakesEconomy`.

#### 2. 🛡️ DrakesPerms / Ranks (Permission System)
-   **Concept**: A lightweight, efficient permission management system (simpler alternative to LuckPerms).
-   **Features**:
    -   Group inheritance & prefixes/suffixes.
    -   Temporary permissions.
    -   MySQL sync for network-wide ranks.
    -   **Chat Formatting**: Integrated chat handling with rank prefixes.

#### 3. 📜 DrakesTab & MOTD
-   **Concept**: Visual enhancement for the server list and tab menu.
-   **Features**:
    -   Animated Tablist headers/footers.
    -   Dynamic MOTD with hex colors and gradients.
    -   "Fake" player count or custom text slots.

#### 4. ⚙️ DrakesTech (Slimefun-like Mechanics)
-   **Concept**: Adds new gameplay depth without client-side mods.
-   **Features**:
    -   **Custom Items**: New tools, weapons, and armor with special abilities.
    -   **Machines**: Multiblock structures for processing resources (Crushers, Smelters).
    -   **Energy System**: Power grid management for machines.
    -   **Recipe Book**: Custom crafting guide using vanilla UI.

#### 5. 💰 DrakesEconomy
-   **Concept**: A robust economy handler hooking into Vault.
-   **Features**:
    -   Multi-currency support (Gold, Tokens, Credits).
    -   Player shops and auction house integration.

## 🛠️ Developer Guide

### Prerequisites
-   **JDK 21**
-   **Maven**
-   **IDE**: IntelliJ IDEA (Recommended) or VS Code

### Building the Project
```bash
mvn clean package
```
This will generate the plugin JAR in the `target/` folder.

### Running Tests (Simulation)
```bash
mvn test
```
Executes all unit tests in `src/test/java`, using MockBukkit to simulate server startup and command execution.

### Code Style
-   Use **MiniMessage** for all text components.
-   Keep logic in `Service` or `Manager` classes; keep the main class clean.
-   Use `ItemBuilder` for all item creation.
