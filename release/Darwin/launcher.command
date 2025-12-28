#!/bin/bash

cd "$(dirname "$0")"

TAG_NAME=`curl -s https://api.github.com/repos/humbertodias/jmugen/tags | jq -r '.[0].name'`
echo "Version: $TAG_NAME"

if [ ! -d "data" ]; then
    echo "Downloading data.zip"
    curl -L https://github.com/humbertodias/jmugen/releases/download/$TAG_NAME/data.zip -o data.zip
    unzip data.zip && rm data.zip
fi

exec jmugen.app/Contents/MacOS/jmugen
