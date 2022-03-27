FROM --platform=linux/amd64 openjdk:17-alpine
RUN mkdir -p /app/bin
COPY ./target/auth-service.jar /app/bin
COPY opentelemetry-javaagent.jar /app/bin
CMD java -javaagent:/app/bin/opentelemetry-javaagent.jar -jar /app/bin/auth-service.jar