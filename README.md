# Kafka Learning Projects

This repository is split into 3 sub-projects, each providing an example of how to use Kafka.
They do not cover all use cases, but offer an easy way to get started with this tool.

## Requirements
- Java 21
- Maven
- Docker

## Getting Started

First, launch the Docker Compose file available at the root folder:
```bash
docker compose up -d
```
Then, start the app via the command line:
```bash
mvn spring-boot:run
```

## URLs

| Service | URL |
|---|---|
| Standalone | http://localhost:8080 |
| Producer | http://localhost:8081 |
| Consumer | http://localhost:8082 |
| Kafka Broker | http://localhost:9092 |
| Prometheus | http://localhost:8080/actuator/prometheus |
| Kafka UI | http://localhost:8090 |
