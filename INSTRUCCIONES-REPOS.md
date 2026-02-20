# Instrucciones para crear y subir repos (DrakesCraft-Labs)

## Requisitos
- Tener `git` instalado.
- Opcional: `gh` (GitHub CLI) autenticado con `gh auth login`.

## Nombres de repos (org DrakesCraft-Labs)
- DrakesCore
- DrakesCrates
- DrakesMotd
- DrakesTab
- DrakesRanks
- DrakesTech

## Ruta A: usando GitHub CLI (recomendado)
1. Abre PowerShell en la raíz del proyecto.
2. Ejecuta:
   ```powershell
   cd Plugins
   ```
3. Para cada plugin, repite este bloque cambiando `REPO`:

   ```powershell
   $REPO = "DrakesCore"   # cambia aquí
   cd $REPO
   git init
   git add -A
   git commit -m "Initial commit"
   gh repo create DrakesCraft-Labs/$REPO --private --source=. --remote=origin --push
   cd ..
   ```

   Cambia `$REPO` por:
   `DrakesCrates`, `DrakesMotd`, `DrakesTab`, `DrakesRanks`, `DrakesTech`.

## Ruta B: sin GitHub CLI (manual en web)
1. Entra a GitHub y crea cada repo vacío en la organización:
   `DrakesCraft-Labs/DrakesCore`, `DrakesCraft-Labs/DrakesCrates`, etc.
2. En PowerShell, entra a `Plugins`:
   ```powershell
   cd Plugins
   ```
3. Para cada plugin, repite este bloque cambiando `REPO`:

   ```powershell
   $REPO = "DrakesCore"   # cambia aquí
   cd $REPO
   git init
   git add -A
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/DrakesCraft-Labs/$REPO.git
   git push -u origin main
   cd ..
   ```

## Nota
Si ya existe `.git` en alguna carpeta y no quieres rehacerlo:
- Omite `git init`.
- Usa `git status` para revisar antes de commitear.
