<p align="center">
  <img src="https://raw.githubusercontent.com/DrakesCraft-Labs/DrakesCore/master/banner.svg" width="100%" alt="DrakesCore animated banner" />
</p>

# DrakesCore (Legacy Monolith)

Este repo es la version monolitica original de la que se extrajeron los plugins `DrakesCrates`, `DrakesMotd`, `DrakesTab`, `DrakesRanks` y `DrakesTech`.

## Rol actual
- Historial y referencia del proyecto original.
- Punto de comparacion para migraciones.
- No es el objetivo final de despliegue si ya usas los repos separados.

## Que contiene
- Clase principal unica que inicializaba todos los modulos.
- API interna (`DrakesCoreAPI`) y utilidades compartidas de mensajes,
  placeholders, items y persistencia PDC/NBT.
- Crates con tipos, llaves, repositorio YAML, preview, editor, animaciones,
  ruleta, validacion economica y entrega de recompensas.
- Rangos con listeners, comandos, integracion de chat y administracion.
- MOTD con estados operativos intercambiables.
- TAB con placeholders, sidebar y economia Vault.
- DrakesTech con red electrica, nodos, generadores, horno electrico,
  multibloques, factories y administracion de maquinas.
- Configuraciones unificadas (`crates.yml`, `motd.yml`, `tab.yml`, `ranks.yml`).

El alcance historico explica por que no debe convivir en produccion con todos
los plugins extraidos: registraria dos veces listeners, comandos y estados del
mismo dominio.

## Estado
- Funcional como plugin unico, pero acoplado.
- La arquitectura nueva recomendada es por plugin separado.
- Conservado como referencia de migracion, no como dependencia comun ni JAR
  agregado del ecosistema actual.

## Migracion hacia repos separados
1. `DrakesCrates`: crates, llaves, editor y preview.
2. `DrakesMotd`: MOTD por estado e iconos.
3. `DrakesTab`: tablist + sidebar.
4. `DrakesRanks`: rangos, permisos y chat.
5. `DrakesTech`: maquinas y energia.

## Que falta en este repo
- Limpieza de codigo duplicado tras la extraccion.
- Definirlo formalmente como `legacy` o mantenerlo como snapshot.
