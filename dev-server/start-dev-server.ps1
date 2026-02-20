param(
    [string]$RuntimePath = (Join-Path (Join-Path (Get-Location) 'dev-server') 'runtime'),
    [string]$MinMemory = '2G',
    [string]$MaxMemory = '4G'
)

$ErrorActionPreference = 'Stop'

if (-not (Test-Path -LiteralPath $RuntimePath)) {
    throw "Runtime path not found: $RuntimePath"
}

$paperJar = Get-ChildItem -Path $RuntimePath -Filter '*.jar' |
    Sort-Object LastWriteTime -Descending |
    Select-Object -First 1

if ($null -eq $paperJar) {
    throw "No server jar found in $RuntimePath. Use prepare-dev-server.ps1 -DownloadPaper"
}

Push-Location $RuntimePath
try {
    & java "-Xms$MinMemory" "-Xmx$MaxMemory" -jar $paperJar.Name nogui
}
finally {
    Pop-Location
}
