# Contexto Maestro DrakesCraft

Responsable: JackStar (Ingeniero en Informatica, fundador de DrakesCraft)

## 1) Estado actual (ya completado)
El monolito original se separo en repos independientes:
- DrakesCore (legacy/base)
- DrakesCrates
- DrakesMotd
- DrakesTab
- DrakesRanks
- DrakesTech

Este repo wrapper ahora se usa para:
- Documentacion global
- Scripts de operacion multi-repo
- Entorno local de integracion

## 2) Vision objetivo
Construir un ecosistema custom tipo SFGuide, pero implementado 100% por DrakesCraft.

Meta principal:
- Progresion completa con guia, modulos, items custom, maquinas, energia, multibloques, armas, armaduras y recetas.

No-meta:
- No depender de Slimefun en runtime.
- Se puede estudiar su codigo como referencia de patrones, no como dependencia directa.

## 3) Integraciones base de plataforma
Base requerida:
- Paper (API del servidor)
- Vault (bridge de servicios)
- EssentialsX Economy (provider de economia via Vault)
- PlaceholderAPI (puente de placeholders entre plugins)

Opcionales recomendadas:
- ProtocolLib (optimizaciones futuras por packets)
- spark (profiling de rendimiento)

## 4) Rol de cada plugin
DrakesCrates:
- Gameplay de crates, llaves, rarezas y pipeline de recompensas.

DrakesMotd:
- Imagen publica del servidor (MOTD por estado + iconos).

DrakesTab:
- Superficie visual en tiempo real (estado de red, economia, rank, tps).

DrakesRanks:
- Ranks/permisos/chat con placeholders para integracion transversal.

DrakesTech:
- Motor principal de progresion custom (guia, maquinas, energia, multiblocks, gear).

## 5) Arquitectura objetivo de DrakesTech
Modulos internos acoplados por interfaces claras:

1. Guide Module
- Entrega libro inicial al primer join.
- El libro abre GUI principal.
- Arbol: Modulos -> Categorias -> Item/Maquina -> Receta.

2. Registry Module
- Registro de IDs estables y versionados.
- Ejemplo: `weapon.blaze_rifle.t1`, `machine.crusher.t1`.

3. Item Module
- Identidad de item custom por PDC.
- Metadatos: tier, rareza, energia, owner-module.

4. Recipe Module
- Recetas declarativas (yaml -> runtime object).
- Tipos: shaped, shapeless, machine recipe, multiblock requirement.

5. Energy Module
- Productores, consumidores y almacenamiento.
- Scheduler por ticks con presupuesto estricto.

6. Multiblock Module
- Matcher de patrones con rotacion.
- Cache de validacion para estructuras activas.

7. Combat Gear Module
- Armas y armaduras custom con habilidades.
- Cooldowns y escalado por tier.

8. Persistence Module
- Inicio: snapshots YAML por mundo/chunk.
- Evolucion: SQL para red multi-servidor.
- Campo `schema-version` para migraciones.

9. Telemetry Module
- Metricas de throughput energetico, costo por tick y maquinas activas.
- Comandos debug para admins.

## 6) Flujo UX (estilo SFGuide)
Primer join del jugador:
1. Recibe `DrakesTech Guide`.
2. Click derecho abre menu de modulos.
3. Click en modulo (ej: Weapons).
4. Lista de armas del modulo.
5. Click en un arma.
6. Menu de detalle:
- Stats
- Requisitos
- Receta visual
- Condiciones de desbloqueo

Mismo patron para Armor, Machines, Energy, Multiblock y Utility.

## 7) Reglas de ingenieria (rendimiento)
- No hacer scans globales pesados cada tick.
- Usar cache por chunk.
- Invalidar cache por eventos de bloque.
- Separar modelo lectura/escritura de estado de maquinas.
- Feature flags en config para sistemas riesgosos.

## 8) Convenciones de datos
- Cada feature con su yaml dedicado.
- Comentarios inline para operadores.
- Opciones peligrosas marcadas con `[DANGER]`.
- IDs tecnicos estables, nunca basados en display-name.

## 9) Roadmap por fases
Fase A: Fundacion
- Guide book + menus base + registry IDs.

Fase B: Recipes UX
- Vista de receta por item/maquina.

Fase C: Machines + Energy v1
- 2 productores, 2 consumidores, 1 almacenamiento.

Fase D: Multiblock v1
- 2 patrones con rotacion.

Fase E: Weapons/Armor v1
- 3 armas, 2 sets de armadura, sistema cooldown.

Fase F: Progression/Balance
- Gates por tier, sinks economicos, loops de rareza.

Fase G: Hardening
- Metricas, debug, tuning de rendimiento.

## 10) Proximo sprint recomendado
1. DrakesTech: entrega de libro inicial + scaffold de menus.
2. DrakesTech: registry de modulos/items/recipes.
3. DrakesTech: primer menu de detalle de receta.
4. Contrato de placeholders DrakesRanks <-> DrakesTab.
5. Entorno local reproducible (ver `dev-server/README.md`).

## 11) Criterios de exito
- El jugador entiende progresion sin wiki externa.
- El servidor mantiene estabilidad TPS con features activas.
- Los plugins siguen independientes pero interoperables por contratos.
