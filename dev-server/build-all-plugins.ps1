param(
    [string]$PluginsPath = (Join-Path (Get-Location) 'Plugins')
)

$ErrorActionPreference = 'Stop'

if (-not (Test-Path -LiteralPath $PluginsPath)) {
    throw "Plugins path not found: $PluginsPath"
}

if (-not (Get-Command mvn -ErrorAction SilentlyContinue)) {
    throw "Maven (mvn) not found in PATH"
}

$repos = @('DrakesCrates', 'DrakesMotd', 'DrakesTab', 'DrakesRanks', 'DrakesTech')

foreach ($repo in $repos) {
    $repoPath = Join-Path $PluginsPath $repo
    if (-not (Test-Path -LiteralPath $repoPath)) {
        Write-Host "[WARN] Missing repo: $repoPath"
        continue
    }

    Write-Host "==> Building $repo"
    mvn -f (Join-Path $repoPath 'pom.xml') clean package -DskipTests
}

Write-Host "[DONE] Build phase completed"
