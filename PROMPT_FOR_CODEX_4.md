# Misión Definitiva para Codex: El Ecosistema Completo de DrakesCore 🌍

Ya tenemos `DrakesCrates` (base funcional) y `DrakesTech` (arquitectura base).
Ahora quiero que cimentes las bases para los **3 Pilares Faltantes** del servidor, y mejores lo que ya existe.

## 1. DrakesCrates (Fase 2: Polish & Features) 📦✨
Actualmente tenemos el comando básico y la animación de ruleta.
Necesito que añadas:
-   **Editor In-Game**: `/drakescrates editor`. Un inventario GUI para modificar las probabilidades de los premios sin tocar el YAML.
-   **Placeholders**: Soporte para PlaceholderAPI (ej: `%drakescrates_keys_physical%` te dice cuántas llaves tienes).
-   **Preview**: Al hacer click izquierdo en una Crate (bloque), abre un inventario pasivo mostrando los posibles premios.

## 2. DrakesMotd (Imagen del Servidor) 🖼️
Crea el paquete `me.jackstar.drakesmotd`.
-   **ServerListPingEvent Listener**:
    -   Intercepta el ping del cliente.
    -   Cambia el MOTD (con soporte MiniMessage y RGB).
    -   **Sistema de "Estados"**: Muestra diferente MOTD si el server está en `MAINTENANCE`, `BETA` o `LIVE`.
    - **Icono Dinámico**: Carga imágenes de una carpeta `icons/` si existe.
    - **Auto-Resize**: Si la imagen no es 64x64, redimensiónala automáticamente usando `java.awt.Graphics2D` para que el usuario pueda meter cualquier PNG.
    - **Hex Colors**: Soporte total de degradadoss (Gradients).

## 3. DrakesTab (Tablist & Scoreboard) 📊
Crea el paquete `me.jackstar.drakestab`.
-   **TabList Manager**:
    -   Header y Footer animados (configurables en YAML con lista de strings que rotan).
    -   Soporte completo de MiniMessage y Placeholders PAPI.
-   **Scoreboard (Sidebar)** - *Opcional pero deseable*:
    -   Sistema anti-flicker (doble buffer o packets).
    -   Muestra Rango, Dinero (Vault), Ping, TPS.

## 4. DrakesRanks (Permisos & Chat) 👑
Crea el paquete `me.jackstar.drakesranks` (Estilo LuckPerms pero ligero).
-   **Rank Object**: Nombre, Prefijo, Sufijo, Color, Peso (Weight), Lista de Nodos de Permiso.
-   **PermissionAttachment**: Inyecta los permisos al jugador al entrar (`PlayerLoginEvent`).
-   **ChatFormatter**:listener `AsyncChatEvent` (Paper) que formatee: `<[Rango] Jugador> Mensaje`.
-   **Comandos**:
    -   `/rank set <player> <rank>`
    -   `/rank create <name>`
    -   `/rank permission add <rank> <node>`

## Requisitos de Arquitectura 🏗️
-   Sigue usando **Domain-Driven Design** donde tenga sentido.
-   Cada módulo (`crates`, `motd`, `tab`, `ranks`) debe tener su propio `Manager` o `Service` que se inicialice en `DrakesCore`.
-   Todo debe ser configurable vía YAML separados (`motd.yml`, `ranks.yml`, etc.).

## 5. Configuración Educativa 📖
**Crucial**: Los YAMLs deben ser **extremadamente configurables** y estar **documentados in-line**:
-   Añade comentarios `#` explicando qué hace cada variable.
-   Si una opción es peligrosa, márcala con `# [DANGER] No tocar a menos que sepas qué haces`.
-   Ejemplo:
    ```yaml
    # Tiempo en ticks entre cada movimiento de la ruleta.
    # Recomendado: 2-5. (Menos de 1 puede causar lag).
    tick-speed: 2
    ```

¡Codex, tienes carta blanca para construir el mejor Core de la historia! Haz que tiemblen los plugins de pago. 😈🚀
