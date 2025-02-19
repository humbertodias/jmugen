#!/bin/bash

# Directory where the script is located
script_dir="$(cd "$(dirname "$0")" && pwd)"
echo "script_dir: $script_dir"

# Download and extract resource.zip if the resource directory doesn't exist
if [ ! -d "$script_dir/resource" ]; then
    echo "Downloading resource.zip..."
    curl -L https://github.com/humbertodias/jmugen/releases/download/v0.01/resource.zip -o "$script_dir/resource.zip"
    
    # Unzip the file into the script's directory
    unzip "$script_dir/resource.zip" -d "$script_dir" && rm "$script_dir/resource.zip"
    
    # Check if the extraction was successful
    if [ ! -d "$script_dir/resource" ]; then
        echo "Error: Resource extraction failed!"
        exit 1
    fi
fi

# Find the jmugen application directory
app_path=$(find "$script_dir" -type d -name "jmugen-*.app" -print -quit)

# Check if the application directory was found
if [ -n "$app_path" ]; then
    echo "Application found at: $app_path"
    
    # Find the main executable file inside the MacOS directory
    main_path=$(find "$app_path/Contents/MacOS" -type f -name "jmugen-*" -print -quit)

    if [ -n "$main_path" ]; then
        # Launch the application with the resource directory argument
        echo "Launching application..."
        "$main_path" -d "$script_dir/resource"
    else
        echo "Error: Main application executable not found!"
        exit 1
    fi

else
    echo "Error: Application not found!"
    exit 1
fi
