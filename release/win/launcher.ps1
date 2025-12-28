# Get the directory where the script is located
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Write-Host "script_dir: $scriptDir"

$tagName = Invoke-RestMethod -Uri "https://api.github.com/repos/humbertodias/jmugen/tags" | Select-Object -First 1 -ExpandProperty name
Write-Host "Version: $tagName"

# Download and extract data.zip if the data directory doesn't exist
if (-not (Test-Path "$scriptDir\data")) {
    Write-Host "Downloading data.zip..."
    Invoke-WebRequest -Uri "https://github.com/humbertodias/jmugen/releases/download/$tagName/data.zip" -OutFile "$scriptDir\data.zip"
    
    # Unzip the file into the script's directory
    Write-Host "Extracting data.zip..."
    Expand-Archive -Path "$scriptDir\data.zip" -DestinationPath "$scriptDir"
    Remove-Item "$scriptDir\data.zip"
}

# Find the jmugen application executable
$mainPath = Get-ChildItem -Path $scriptDir -Recurse -File -Filter "jmugen-*.exe" | Select-Object -First 1

# Check if the application executable was found
if ($mainPath) {
    Write-Host "Application found at: $mainPath.FullName"

    # Launch the application with the data directory argument
    Write-Host "Launching application..."
    Start-Process -FilePath $mainPath.FullName -ArgumentList "-d `"$scriptDir\data`""
} else {
    Write-Host "Error: Main application executable not found!"
    Exit 1
}
