param(
    [string]$Root = (Get-Location).Path,
    [string]$Owner = "",
    [string]$RemoteBase = "",
    [switch]$IncludeLicense
)

$ErrorActionPreference = "Stop"

$plugins = @(
    "DrakesCrates",
    "DrakesMotd",
    "DrakesTab",
    "DrakesRanks",
    "DrakesTech"
)

function Ensure-Git {
    if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
        throw "git no esta disponible en PATH."
    }
}

Ensure-Git

foreach ($name in $plugins) {
    $path = Join-Path $Root $name
    if (-not (Test-Path $path)) {
        Write-Host "[SKIP] No existe: $path"
        continue
    }

    Write-Host "[INFO] Inicializando repo: $name"

    Push-Location $path
    try {
        if (-not (Test-Path (Join-Path $path ".git"))) {
            git init | Out-Null
        }

        if ($IncludeLicense -and (Test-Path (Join-Path $Root "LICENSE"))) {
            Copy-Item -Force (Join-Path $Root "LICENSE") (Join-Path $path "LICENSE")
        }

        if (-not (Test-Path (Join-Path $path "README.md"))) {
            "# $name" | Set-Content -Path (Join-Path $path "README.md")
        }

        git add -A

        $status = git status --porcelain
        if ($status) {
            git commit -m "Initial split of $name" | Out-Null
        }

        if ($RemoteBase -ne "" -and $Owner -ne "") {
            $remote = "$RemoteBase/$Owner/$name.git"
            $existing = git remote | Select-String -SimpleMatch "origin"
            if (-not $existing) {
                git remote add origin $remote
            }
            Write-Host "[INFO] Remote origin: $remote"
        }
    }
    finally {
        Pop-Location
    }
}

Write-Host "Listo."
