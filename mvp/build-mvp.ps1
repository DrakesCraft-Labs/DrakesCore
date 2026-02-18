param()

$ErrorActionPreference = "Stop"

Push-Location $PSScriptRoot
mvn -q clean package

$dest = Join-Path $PSScriptRoot "target\jars"
if (Test-Path $dest) { Remove-Item $dest -Recurse -Force }
New-Item -ItemType Directory -Path $dest | Out-Null

Get-ChildItem -Recurse -Filter *.jar | Where-Object {
    $_.FullName -like "*\target\*" -and $_.FullName -notlike "*\target\jars\*"
} | ForEach-Object {
    Copy-Item $_.FullName -Destination $dest
}

Write-Host "JARs copied to $dest"
Get-ChildItem $dest | Select-Object Name,Length
Pop-Location
