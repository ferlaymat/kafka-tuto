This repository is split into 3 sub-projects which all both give an example on how to use kafka. They do not cover all the cases but it s an easy waay to start with this tool. 
Requirements: Java 21, Maven and Docker
To run the app, please launch firstly the docker compose file available at the root folder. Then, in a second step, launch the app throught the command line: mvn spring-boot:run

Here,  you'll find the main url:

standalone: http://localhost:8080
producer: http://localhost:8081
consumer: http://localhost:8082

Kafka broker: http://localhost:9092
Metrics:
 * Prometheus: http://localhost:8080/actuator/prometheus
 * Kafka-ui: http://localhost:8090
