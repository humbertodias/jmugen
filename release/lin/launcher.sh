#!/bin/bash

# Directory where the script is located
script_dir="$(cd "$(dirname "$0")" && pwd)"
echo "script_dir: $script_dir"

# Download and extract data.zip if the data directory doesn't exist
if [ ! -d "$script_dir/data" ]; then
    echo "Downloading data.zip..."
    curl -L https://github.com/humbertodias/jmugen/releases/download/v0.01/data.zip -o "$script_dir/data.zip"
    
    # Unzip the file into the script's directory
    unzip "$script_dir/data.zip" -d "$script_dir" && rm "$script_dir/data.zip"
    
    # Check if the extraction was successful
    if [ ! -d "$script_dir/data" ]; then
        echo "Error: data extraction failed!"
        exit 1
    fi
fi

main_path="$script_dir/jmugen"
if [ -n "$main_path" ]; then
    echo "Launching application..."
    "$main_path" -d "$script_dir/data"
else
    echo "Error: Main application executable not found!"
    exit 1
fi
