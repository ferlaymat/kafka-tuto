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
| Standalone | http://localhost:8080 |Standalone project to produce/consume a topic and provides KStream example |
| Producer | http://localhost:8081 |Simple project to produce a topic|
| Consumer | http://localhost:8082 |Simple project to consume a topic|
| offset | http://localhost:8083 |Explain some configurations and provides at-least-once/at-most-once examples to manage offset acknowledgment|
| batch | http://localhost:8084 |Without Batching, each message is sent directly to the broker.One message equals one round trip on the network. With batching, messages are accumulated in a buffert and only one packet is sent|
| Kafka Broker | http://localhost:9092 | NA |
| Prometheus | http://localhost:8080/actuator/prometheus | NA |
| Kafka UI | http://localhost:8090 | NA |
