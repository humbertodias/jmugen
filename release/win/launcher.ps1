# Get the directory where the script is located
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Write-Host "script_dir: $scriptDir"

# Download and extract resource.zip if the resource directory doesn't exist
if (-not (Test-Path "$scriptDir\resource")) {
    Write-Host "Downloading resource.zip..."
    Invoke-WebRequest -Uri "https://github.com/humbertodias/jmugen/releases/download/v0.01/resource.zip" -OutFile "$scriptDir\resource.zip"
    
    # Unzip the file into the script's directory
    Write-Host "Extracting resource.zip..."
    Expand-Archive -Path "$scriptDir\resource.zip" -DestinationPath "$scriptDir"
    Remove-Item "$scriptDir\resource.zip"
    
    # Check if the extraction was successful
    if (-not (Test-Path "$scriptDir\resource")) {
        Write-Host "Error: Resource extraction failed!"
        Exit 1
    }
}

# Find the jmugen application directory
$appPath = Get-ChildItem -Path $scriptDir -Recurse -Directory -Filter "jmugen-*.app" | Select-Object -First 1

# Check if the application directory was found
if ($appPath) {
    Write-Host "Application found at: $appPath"
    
    # Find the main executable file inside the MacOS directory
    $mainPath = Get-ChildItem -Path "$appPath\Contents\MacOS" -Recurse -File -Filter "jmugen-*" | Select-Object -First 1

    if ($mainPath) {
        # Launch the application with the resource directory argument
        Write-Host "Launching application..."
        Start-Process -FilePath $mainPath.FullName -ArgumentList "-d $scriptDir\resource"
    } else {
        Write-Host "Error: Main application executable not found!"
        Exit 1
    }
} else {
    Write-Host "Error: Application not found!"
    Exit 1
}
