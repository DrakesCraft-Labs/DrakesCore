param(
    [string]$PluginsPath = (Join-Path (Get-Location) 'Plugins')
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
    git -C $repo.FullName rev-parse --abbrev-ref HEAD
    git -C $repo.FullName status --short
    Write-Host ""
}
