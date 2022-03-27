#!/bin/bash

mvn clean install package -DskipTests
cp opentelemetry-javaagent-all.jar print-service/
docker-compose up --build