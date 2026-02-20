# DrakesCrates

## Qué es
Sistema de crates con editor in-game, animación de ruleta, previsualización y soporte de llaves físicas.

## Arquitectura
- `application/` use cases + repositorios.
- `domain/` modelos (Crate, Reward, Key).
- `infrastructure/` YAML + settings.
- `presentation/` comandos, listeners, editor y animación.

## Hecho
- `/drakescrates givekey|editor|reload`.
- Editor in-game para probabilidades.
- Preview pasivo al click izquierdo.
- Animación de ruleta.
- PlaceholderAPI: `%drakescrates_keys_physical%`.

## Falta
- Placeholders por ID de llave (ej. `%drakescrates_keys_physical_<keyid>%`).
- Comando de reload que también recargue `crates-settings.yml`.
- Validación opcional de chances totales en editor.

## Configuración
- `crates.yml` y `crates-settings.yml`.
- Comentarios in-line ya incluidos.

## Dependencias
- Paper 1.20.6
- Java 21
- PlaceholderAPI (opcional)
