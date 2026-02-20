# DrakesCraft Plugin Ecosystem (Wrapper Repo)

Este repositorio raiz funciona como contenedor y organizador del ecosistema.

## Repos oficiales
- DrakesCore (legacy/base): https://github.com/DrakesCraft-Labs/DrakesCore
- DrakesCrates: https://github.com/DrakesCraft-Labs/DrakesCrates
- DrakesMotd: https://github.com/DrakesCraft-Labs/DrakesMotd
- DrakesTab: https://github.com/DrakesCraft-Labs/DrakesTab
- DrakesRanks: https://github.com/DrakesCraft-Labs/DrakesRanks
- DrakesTech: https://github.com/DrakesCraft-Labs/DrakesTech

## Estructura local esperada
- `Plugins/DrakesCore`
- `Plugins/DrakesCrates`
- `Plugins/DrakesMotd`
- `Plugins/DrakesTab`
- `Plugins/DrakesRanks`
- `Plugins/DrakesTech`

## Flujo recomendado
1. Trabajar cambios dentro del repo plugin correspondiente.
2. Commitear y pushear por plugin.
3. Usar scripts de raiz para operar todo junto.

## Scripts utiles
- `status-all.ps1`: estado de todos los repos en `Plugins/`.
- `push-all.ps1`: add/commit/push masivo en todos los repos.
- `repo-select.ps1`: selector interactivo para elegir repo.

## Notas
- Este repo raiz no deberia trackear el contenido interno de `Plugins/`.
- Si aparece ruido en `git status` del wrapper, quitar `Plugins/` del tracking en este repo.
