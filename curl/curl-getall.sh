#!/bin/bash

curl -X GET "http://localhost:8080/clickhouse/getAll?limit=1000" | jq

