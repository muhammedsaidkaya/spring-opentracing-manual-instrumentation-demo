FROM --platform=linux/amd64 openjdk:17-alpine
RUN mkdir -p /app/bin
COPY ./target/auth-service.jar /app/bin
CMD java -jar /app/bin/auth-service.jar