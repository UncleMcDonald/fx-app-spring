#!/usr/bin/env bash
set -euo pipefail

# success: 100 EUR -> USD converts at 1.0818, retail 1% fee applied
curl -i -X POST localhost:8080/api/conversions \
  -H "Content-Type: application/json" \
  -d '{"base":"EUR","quote":"USD","amount":100}'

# unknown pair -> 404 JSON, not a stack trace
curl -i localhost:8080/api/rates/EUR/XXX

# bad payload (negative amount) -> 400 JSON via the validation handler
curl -i -X POST localhost:8080/api/conversions \
  -H "Content-Type: application/json" \
  -d '{"base":"EUR","quote":"USD","amount":-5}'

# malformed JSON -> 400 JSON via the message-readable handler
curl -i -X POST localhost:8080/api/conversions \
  -H "Content-Type: application/json" \
  -d 'not json'
