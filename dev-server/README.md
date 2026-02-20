# Servidor Local de Integracion (Sandbox)

Esta carpeta sirve para pruebas end-to-end de los plugins DrakesCraft sin tocar produccion.

## Stack base requerido
Requeridos:
- Java 21
- Paper 1.20.6

Plugins externos base:
- Vault (bridge de servicios)
- PlaceholderAPI (bridge de placeholders)
- EssentialsX Economy (o cualquier provider compatible con Vault)

Recomendados:
- ProtocolLib (features futuras por packets)
- spark (profiling y tuning)

## 1) Build masivo de plugins
Desde la raiz del wrapper:
```powershell
.\dev-server\build-all-plugins.ps1
```

## 2) Preparar runtime
```powershell
.\dev-server\prepare-dev-server.ps1 -DownloadPaper -AcceptEula
```

Esto:
- Crea `dev-server/runtime`
- Descarga la build mas reciente de Paper `1.20.6` (si activas `-DownloadPaper`)
- Copia los jars build de cada plugin a `runtime/plugins`
- Escribe `eula=true` (si activas `-AcceptEula`)

## 3) Agregar dependencias externas manualmente
Copia estos jars dentro de `dev-server/runtime/plugins`:
- Vault
- PlaceholderAPI
- EssentialsX (con Economy habilitada)

## 4) Iniciar servidor local
```powershell
.\dev-server\start-dev-server.ps1 -MinMemory 2G -MaxMemory 4G
```

## 5) Checklist de smoke test
1. Entrar con usuario limpio.
2. Confirmar inicio de DrakesTech sin errores.
3. Confirmar header/footer y sidebar de DrakesTab.
4. Confirmar formato chat y permisos de DrakesRanks.
5. Confirmar editor/preview/keys de DrakesCrates.
6. Confirmar estado + icono de DrakesMotd.

## 6) Siguiente automatizacion sugerida
- Script de smoke logs (`latest.log`) para detectar ERROR/FATAL automaticamente.
- Script de seed de configuraciones de prueba por modulo.
