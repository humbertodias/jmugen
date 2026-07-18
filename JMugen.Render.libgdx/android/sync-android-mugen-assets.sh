#!/usr/bin/env bash
# Copy Mugen data/ into src/main/assets/data so Android Studio / AGP can package assets without running Maven.
set -euo pipefail
MODULE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_DATA="${MODULE_DIR}/../../data"
DEST="${MODULE_DIR}/src/main/assets/data"
if [[ ! -d "$REPO_DATA" ]]; then
  echo "Expected game data at: $REPO_DATA" >&2
  exit 1
fi
if command -v rsync >/dev/null 2>&1; then
  rsync -a --delete "${REPO_DATA}/" "${DEST}/"
else
  rm -rf "${DEST}"
  mkdir -p "${DEST}"
  cp -a "${REPO_DATA}/." "${DEST}/"
fi
echo "Synced game data to ${DEST}"
