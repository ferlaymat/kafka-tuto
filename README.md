# Kafka Learning Projects

This repository is split into several sub-projects, each providing an example of how to use Kafka.
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

## Projects and URLs

| Service | URL | Use case |
|---|---|---|
| Standalone | http://localhost:8080 |Standalone project to produce/consume a topic and give KStream example |
| Producer | http://localhost:8081 |Simple project to produce a topic|
| Consumer | http://localhost:8082 |Simple project to consume a topic|
| offset | http://localhost:8083 |Explain some configuration and give at-least-once/at-most-once examples to manage offset acknowledgment|
| batch | http://localhost:8084 |Without Batching, each send is directly provided to the broker.1 message equals 1 round trip in the network. With batching, messages are stacked in a buffert and only 1 packet is sent|
| Kafka Broker | http://localhost:9092 | NA |
| Prometheus | http://localhost:8080/actuator/prometheus | NA |
| Kafka UI | http://localhost:8090 | NA |
