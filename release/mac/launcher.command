#!/bin/bash

cd "$(dirname "$0")"

if [ ! -d "data" ]; then
    echo "Downloading data.zip"
    curl -L https://github.com/humbertodias/jmugen/releases/download/v0.01/data.zip -o data.zip
    unzip data.zip && rm data.zip
fi

exec jmugen.app/Contents/MacOS/jmugen -d data
