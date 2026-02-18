# Misión 2 para Codex: Integración Final de DrakesCrates 🔗

¡Gran trabajo con la Infraestructura y la Animación! El sistema de Roulette y el YAML Loader se ven sólidos.

Ahora necesitamos **conectar los cables** para que esto funcione en el juego.
Tu misión es implementar la capa de **Presentación (Comandos/Listeners)** y el **Wiring** en la clase principal.

## 1. El Comando Admin (`/drakescrates`) ⌨️
Crea `DrakesCratesCommand` (implementa `CommandExecutor`):
-   `/drakescrates givekey <player> <key_id> <amount>`:
    -   Busca la `Key` en el `CrateRepository`.
    -   Da el item físico al jugador (usando `key.getItem()`).
    -   Mensaje de éxito/error con MiniMessage.
-   `/drakescrates reload`:
    -   Recarga el `YamlCrateRepository`.

## 2. El Listener de Interacción (`CrateListener`) 🖱️
Crea `CrateListener` (implementa `Listener`):
-   Evento `PlayerInteractEvent`:
    -   Detecta click derecho en un bloque.
    -   Busca si esa `Location` corresponde a una `Crate` (usando `repo.findCrateByLocation(loc)`).
    -   Si es una Crate:
        -   Cancela el evento original.
        -   Ejecuta `OpenCrateUseCase`.
        -   Si `result.isSuccess()`:
            -   Lanza `RouletteAnimation.start()`.
        -   Si falla:
            -   Envía `result.getMessage()` al jugador (rojo).

## 3. Wiring en `DrakesCore` 🔌
Dime exactamente qué líneas agregar en `DrakesCore.java` para:
-   Inicializar `YamlCrateRepository` (y cargar `crates.yml`).
-   Registrar el comando y el listener.
-   (Opcional) Guardar el `config.yml` y `crates.yml` por defecto si no existen.

## 4. [NUEVO] DrakesTech: Base Tecnológica (Slimefun Style) ⚛️
Quiero que aproveches tu potencia para **bosquejar la arquitectura** de `DrakesTech`.
Crea la estructura de paquetes y las interfaces clave en `me.jackstar.drakestech`:
-   **NBT Handler**: Sistema robusto para identificar items custom (usando `PersistentDataContainer`, no strings).
-   **AbstractMachine**: Clase base para máquinas multiblock.
    -   Debe manejar `Location`, `Rotation`, `Inventory`.
-   **EnergyNetwork**: Interfaz para cables y flujo de energía.
-   **MultiblockDetector**: Lógica para detectar si una estructura en el mundo es válida (ej: Reactor Nuclear).
-   **CustomHeads**: Enum o Factory para obtener cabezas con textura Base64.

No implementes toda la lógica al 100%, pero sí las **Interfaces** y **Clases Abstractas** para que yo solo tenga que rellenar los huecos.

¡Haz que todo fluya! Quiero ver ese inventario girando y las máquinas listas para ser programadas. 🎰✨🏭
