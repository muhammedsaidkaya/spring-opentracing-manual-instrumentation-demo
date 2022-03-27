#!/bin/bash

mvn clean install package -DskipTests
cp opentelemetry-javaagent.jar print-service/
cp opentelemetry-javaagent.jar auth-service/
docker-compose up --build