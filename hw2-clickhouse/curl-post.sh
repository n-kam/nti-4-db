#!/bin/bash

curl -X POST "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
    "timestamp": "2023-04-26T12:22:05.525+03:00",
    "source": "T-1",
    "value": 123.2
	}' | jq

curl -X POST "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
    "timestamp": "2023-04-26T12:22:05.654+03:00",
    "source": "T-1",
    "value": 5464
	}' | jq

curl -X POST "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
    "timestamp": "2023-04-26T12:22:05.723+03:00",
    "source": "T-1",
    "value": 4545
	}' | jq

curl -X POST "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
    "timestamp": "2023-04-26T12:22:05.771+03:00",
    "source": "T-1",
    "value": 478.11
	}' | jq

curl -X POST "http://localhost:8080/kafka/send" \
   -H 'Content-Type: application/json' \
   -d '{
    "timestamp": "2023-04-26T12:22:05.816+03:00",
    "source": "T-2",
    "value": 2589
	}' | jq
