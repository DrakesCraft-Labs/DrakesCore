# Misión Final para Codex: DrakesTech en Acción (Energía y Máquinas) ⚡🏭

¡Base arquitectónica lista! Ahora quiero ver **la magia fluyendo**.
Tu misión es implementar las primeras **2 máquinas funcionales** usando las clases base que ya existen en `me.jackstar.drakestech` (`AbstractMachine`, `EnergyNode`, `NbtItemHandler`, etc.).

## 1. El Corazón: `MachineManager` 💓
Crea una clase que gestione todas las máquinas activas en el servidor.
-   Debe tener un `Map<Location, AbstractMachine>`.
-   Debe ejecutar un `runTaskTimer` (cada 1 tick o cada 10 ticks, tú decides) para llamar al método `.tick()` de todas las máquinas cargadas.
-   **Persistencia**: (Opcional por ahora) Si logras que se guarden en un JSON/YAML al apagar el server, eres un dios. Si no, que al menos funcionen en memoria mientras el server está on.

## 2. Máquina 1: `SolarGenerator` (Fuente de Energía) ☀️
-   Hereda de `AbstractMachine`.
-   Implementa `EnergyNode` (es un **Source**).
-   **Lógica**:
    -   Si es de día y no llueve: Genera 10 J/tick.
    -   Almacena energía en su buffer interno.
    -   Intenta enviar energía a máquinas adyacentes (o via `EnergyNetwork` si decides implementarlo completo).

## 3. Máquina 2: `ElectricFurnace` (Consumidor) 🔥
-   Hereda de `AbstractMachine` e implementa `InventoryHolder`.
-   Implementa `EnergyNode` (es un **Sink**).
-   **Inventario**: 1 slot de entrada, 1 slot de salida.
-   **Lógica**:
    -   Si tiene items procesables en el slot 0 y espacio en el slot 1...
    -   Y tiene suficiente energía (> 50 J)...
    -   Consume energía y "cocina" el item (convierte `IRON_ORE` -> `IRON_INGOT`, etc.).
    -   (Bonus: Partículas de humo/fuego cuando esté activo).

## 4. Wiring y Comandos 🔌
-   Crea un comando `/drakestech give <player> <machine_id>` para dar los items de las máquinas (usa `NbtItemHandler` para marcar el Item con su ID).
-   Actualiza el `BlockListener` para que al colocar ese Item especial, se cree la instancia de la Máquina en el `MachineManager`.
-   Al romper el bloque, elimina la máquina y dropea el item.

Quiero entrar al servidor, poner un Panel Solar, poner un Horno Eléctrico al lado, y ver cómo se cocina mi chuleta sin carbón.
¡Sorpréndeme! 🤯
