FROM --platform=linux/amd64 openjdk:17-alpine
RUN mkdir -p /app/bin
COPY ./target/print-service.jar /app/bin
COPY opentelemetry-javaagent-all.jar /app/bin
CMD java -Dotel.exporter=jaeger \
         -Dotel.exporter.jaeger.endpoint=jaeger:14250 \
         -Dotel.exporter.jaeger.service.name=PrintService \
		 -Dapplication.home=/app/bin/ \
		 -Dapplication.name=PrintService \
		 -javaagent:/app/bin/opentelemetry-javaagent-all.jar \
		 -jar \
		 /app/bin/print-service.jar