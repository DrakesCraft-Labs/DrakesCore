param(
    [string]$PluginsPath = (Join-Path (Get-Location) 'Plugins'),
    [string]$Message = "Update plugins $(Get-Date -Format 'yyyy-MM-dd HH:mm')",
    [switch]$NoCommit,
    [switch]$NoPush
)

$ErrorActionPreference = 'Stop'

if (-not (Test-Path -LiteralPath $PluginsPath)) {
    throw "No existe la carpeta de plugins: $PluginsPath"
}

$repos = Get-ChildItem -LiteralPath $PluginsPath -Directory |
    Where-Object { Test-Path -LiteralPath (Join-Path $_.FullName '.git') } |
    Sort-Object Name

if (-not $repos) {
    Write-Host "No se encontraron repos git dentro de: $PluginsPath"
    exit 0
}

foreach ($repo in $repos) {
    Write-Host "==> $($repo.Name)"

    git -C $repo.FullName add -A

    if (-not $NoCommit) {
        $hasChanges = git -C $repo.FullName status --porcelain
        if ($hasChanges) {
            git -C $repo.FullName commit -m $Message
        } else {
            Write-Host "Sin cambios para commitear."
        }
    }

    if (-not $NoPush) {
        git -C $repo.FullName push
    }

    Write-Host ""
}
