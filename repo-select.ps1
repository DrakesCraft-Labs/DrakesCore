param(
    [string]$PluginsPath = (Join-Path (Get-Location) 'Plugins'),
    [switch]$OpenNewWindow
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

Write-Host "En que repo quieres moverte?"
for ($i = 0; $i -lt $repos.Count; $i++) {
    Write-Host "[$($i + 1)] $($repos[$i].Name)"
}

$raw = Read-Host 'Seleccion (numero)'
$num = 0
if (-not [int]::TryParse($raw, [ref]$num)) {
    throw "Seleccion invalida: $raw"
}

$index = $num - 1
if ($index -lt 0 -or $index -ge $repos.Count) {
    throw "Seleccion fuera de rango: $raw"
}

$target = $repos[$index].FullName

if ($OpenNewWindow) {
    Start-Process powershell -ArgumentList '-NoExit', '-Command', "Set-Location -LiteralPath '$target'"
    Write-Host "Abierto en nueva ventana: $target"
    exit 0
}

if ($MyInvocation.InvocationName -eq '.') {
    Set-Location -LiteralPath $target
    Write-Host "Ahora estas en: $target"
} else {
    Write-Host "Repo seleccionado: $target"
    Write-Host "Para moverte en esta misma terminal, ejecuta:"
    Write-Host ". .\repo-select.ps1"
    Write-Host "O abre nueva ventana con:"
    Write-Host ".\repo-select.ps1 -OpenNewWindow"
}
