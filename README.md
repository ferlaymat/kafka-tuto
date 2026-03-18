Requirements: Need Maven and Docker install locally
To run the app, please launch firstly the docker compose file available at the root folder. Then, ina second step, launch the app throught the command line: mvn spring-boot:run
Kafka broker: http://localhost:9092

Metrics:
 * Prometheus: http://localhost:8080/actuator/prometheus
 * Kafka-ui: http://localhost:8090
