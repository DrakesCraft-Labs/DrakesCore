# Misión para Codex: Infraestructura de DrakesCrates 🏗️

Hola Codex. Ya tenemos el Core refactorizado y el Dominio de `DrakesCrates` (`Crate`, `Key`, `Reward`, `UseCase`) listo en `src/main/java/me/jackstar/drakescrates`.

Ahora necesito que hagas el **trabajo pesado** de la capa de **Infraestructura** y **Presentación**.
Necesito que generes el código para los siguientes sistemas:

## 1. Persistencia (YAML Repository) 💾
Crea una interfaz `CrateRepository` y su implementación `YamlCrateRepository`.
Debe cargar la configuración desde un archivo `crates.yml` (te inventas la estructura YAML lógica):
-   **Parser de Items**: Debe leer Material, Nombre (MiniMessage), Lore, Enchantments, CustomModelData.
-   **Parser de Rewards**: Debe leer Chance (probabilidad), Comandos (lista de strings) e Item a mostrar.
-   **Validación**: Si una Crate pide `PHYSICAL_KEY`, debe buscar la definición de la Key correspondiente.

## 2. Sistema de Animaciones (GUI Framework) 🎰
Implementa el sistema visual para abrir las cajas.
-   Interfaz `CrateAnimation`: Método `start(Player player, Crate crate, Reward winReward)`.
-   Implementación `RouletteAnimation` (Estilo CS:GO):
    -   Crea un Inventario de 27 slots (9x3).
    -   Usa un `BukkitRunnable` para mover los items de derecha a izquierda.
    -   El item ganador debe detenerse en el centro (slot 13).
    -   Efectos de sonido (Sound.BLOCK_NOTE_BLOCK_PLING) al pasar items.
    -   Al finalizar, cierra el inventario y da el premio.

## Requisitos Técnicos ⚙️
-   Usa **Paper API 1.20.6**.
-   Usa **MiniMessage** para todos los textos y colores.
-   Maneja excepciones de forma segura (no crashees el server si el YAML está mal).

¡Sorpréndeme con una implementación robusta y lista para producción! genérolo todo en las carpetas correspondientes (`infrastructure`, `presentation`).
