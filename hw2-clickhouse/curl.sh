#!/bin/bash

curl -X GET "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
	"timestamp": "2023-04-20T12:52:23.740723557Z",
	"source": "trans 1242",
	"value": 234.44 
	}' | jq

curl -X GET "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
	"timestamp": "2023-04-20T12:52:24.740723557Z",
	"source": "trans 1242",
	"value": 234.54 
	}' | jq
curl -X GET "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
	"timestamp": "2023-04-20T12:52:25.740723557Z",
	"source": "trans 1242",
	"value": 234.34 
	}' | jq
curl -X GET "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
	"timestamp": "2023-04-20T12:52:26.740723557Z",
	"source": "trans 1242",
	"value": 234.74 
	}' | jq
curl -X GET "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
	"timestamp": "2023-04-20T12:52:27.740723557Z",
	"source": "trans 1242",
	"value": 234.24 
	}' | jq
curl -X GET "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
	"timestamp": "2023-04-20T12:52:28.740723557Z",
	"source": "trans 1242",
	"value": 234.84 
	}' | jq

curl -X GET "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
	"timestamp": "2023-04-20T12:52:29.740723557Z",
	"source": "trans 1242",
	"value": 234.34 
	}' | jq


curl -X GET "http://localhost:8080/clickhouse/getAll" | jq
