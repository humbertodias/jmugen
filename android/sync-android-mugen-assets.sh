#!/usr/bin/env bash
# Copy Mugen assets/ into src/main/assets/data so Android Studio / AGP can package assets without Gradle.
set -euo pipefail
MODULE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ASSETS="${MODULE_DIR}/../assets"
DEST="${MODULE_DIR}/src/main/assets/data"
if [[ ! -d "$REPO_ASSETS" ]]; then
  echo "Expected game assets at: $REPO_ASSETS" >&2
  exit 1
fi
if command -v rsync >/dev/null 2>&1; then
  rsync -a --delete "${REPO_ASSETS}/" "${DEST}/"
else
  rm -rf "${DEST}"
  mkdir -p "${DEST}"
  cp -a "${REPO_ASSETS}/." "${DEST}/"
fi
echo "Synced game assets to ${DEST}"
