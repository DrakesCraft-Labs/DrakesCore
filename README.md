# DrakesCore (Legacy Monolith)

Este repo es la version monolitica original de la que se extrajeron los plugins `DrakesCrates`, `DrakesMotd`, `DrakesTab`, `DrakesRanks` y `DrakesTech`.

## Rol actual
- Historial y referencia del proyecto original.
- Punto de comparacion para migraciones.
- No es el objetivo final de despliegue si ya usas los repos separados.

## Que contiene
- Clase principal unica que inicializaba todos los modulos.
- Utilidades compartidas (`MessageUtils`, `PlaceholderUtils`, `ItemBuilder`).
- Configuraciones unificadas (`crates.yml`, `motd.yml`, `tab.yml`, `ranks.yml`).

## Estado
- Funcional como plugin unico, pero acoplado.
- La arquitectura nueva recomendada es por plugin separado.

## Migracion hacia repos separados
1. `DrakesCrates`: crates, llaves, editor y preview.
2. `DrakesMotd`: MOTD por estado e iconos.
3. `DrakesTab`: tablist + sidebar.
4. `DrakesRanks`: rangos, permisos y chat.
5. `DrakesTech`: maquinas y energia.

## Que falta en este repo
- Limpieza de codigo duplicado tras la extraccion.
- Definirlo formalmente como `legacy` o mantenerlo como snapshot.
