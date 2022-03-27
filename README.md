
# Tracing in Spring Boot with OpenTracing/OpenTelemetry

```
sh build.sh
```

# Development
```
sh dev-build.sh

JVM Options for Print Service
-javaagent:opentelemetry-javaagent.jar
-Dotel.traces.exporter=zipkin
-Dotel.exporter.zipkin.endpoint=http://localhost:9411/api/v2/spans
-Dotel.service.name=print-service

JVM Options for Auth Service (Active Profile:dev)
-javaagent:opentelemetry-javaagent.jar
-Dotel.traces.exporter=zipkin
-Dotel.exporter.zipkin.endpoint=http://localhost:9411/api/v2/spans
-Dotel.service.name=print-service
```

## Auth-Service
* For API-Swagger: http://localhost:9090/v2/api-docs
* Inject Opentracing Agent as a Dependency (Spring Filter)
* Exporter Options: Jaeger or Zipkin
* Custom span can be created.

## Print-Service
* For API-Swagger: http://localhost:9091/v2/api-docs
* Inject OpenTelemetry Agent as a Javaagent (Otel Collector)
* Otel Exporter Options: Jaeger


## Jaeger
```
http://localhost:16686
```
