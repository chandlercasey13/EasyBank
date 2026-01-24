#!/bin/sh
set -e

mkdir -p /app/certs

if [ -z "$SSL_KEYSTORE_B64" ]; then
  echo "ERROR: SSL_KEYSTORE_B64 is not set"
  exit 1
fi

# Decode into the path Spring expects
echo "$SSL_KEYSTORE_B64" | base64 -d > /app/certs/cloudflare-origin.p12
chmod 600 /app/certs/cloudflare-origin.p12

exec sh -c "java $JAVA_OPTS -jar /app/app.jar"
