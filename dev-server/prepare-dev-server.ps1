param(
    [string]$RuntimePath = (Join-Path (Join-Path (Get-Location) 'dev-server') 'runtime'),
    [string]$PluginsPath = (Join-Path (Get-Location) 'Plugins'),
    [string]$PaperVersion = '1.20.6',
    [switch]$DownloadPaper,
    [switch]$AcceptEula
)

$ErrorActionPreference = 'Stop'

function Ensure-Dir([string]$Path) {
    if (-not (Test-Path -LiteralPath $Path)) {
        New-Item -ItemType Directory -Path $Path | Out-Null
    }
}

Ensure-Dir $RuntimePath
$pluginsDir = Join-Path $RuntimePath 'plugins'
Ensure-Dir $pluginsDir

if ($AcceptEula) {
    Set-Content -Path (Join-Path $RuntimePath 'eula.txt') -Value 'eula=true'
}

if ($DownloadPaper) {
    Write-Host "[INFO] Resolving latest Paper build for $PaperVersion"
    $buildsApi = "https://api.papermc.io/v2/projects/paper/versions/$PaperVersion"
    $buildsData = Invoke-RestMethod -Uri $buildsApi
    if (-not $buildsData.builds -or $buildsData.builds.Count -eq 0) {
        throw "No builds found for Paper version $PaperVersion"
    }

    $latestBuild = ($buildsData.builds | Sort-Object)[-1]
    $buildApi = "https://api.papermc.io/v2/projects/paper/versions/$PaperVersion/builds/$latestBuild"
    $buildData = Invoke-RestMethod -Uri $buildApi
    $jarName = $buildData.downloads.application.name
    $jarUrl = "https://api.papermc.io/v2/projects/paper/versions/$PaperVersion/builds/$latestBuild/downloads/$jarName"

    Write-Host "[INFO] Downloading $jarName"
    Invoke-WebRequest -Uri $jarUrl -OutFile (Join-Path $RuntimePath $jarName)
}

$pluginRepos = @('DrakesCrates', 'DrakesMotd', 'DrakesTab', 'DrakesRanks', 'DrakesTech')

foreach ($repoName in $pluginRepos) {
    $repoPath = Join-Path $PluginsPath $repoName
    if (-not (Test-Path -LiteralPath $repoPath)) {
        Write-Host "[WARN] Repo not found: $repoPath"
        continue
    }

    $targetPath = Join-Path $repoPath 'target'
    if (-not (Test-Path -LiteralPath $targetPath)) {
        Write-Host "[WARN] No target folder in $repoName. Build it first with: mvn clean package"
        continue
    }

    $jar = Get-ChildItem -Path $targetPath -Filter '*.jar' |
        Where-Object { $_.Name -notlike 'original-*' } |
        Sort-Object LastWriteTime -Descending |
        Select-Object -First 1

    if ($null -eq $jar) {
        Write-Host "[WARN] No JAR found in $targetPath"
        continue
    }

    Copy-Item -Force -Path $jar.FullName -Destination (Join-Path $pluginsDir $jar.Name)
    Write-Host "[OK] Copied $repoName -> $($jar.Name)"
}

Write-Host "[DONE] Dev server prepared at: $RuntimePath"
