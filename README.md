
# Tracing in Spring Boot with OpenTracing/OpenTelemetry

```
sh build.sh
```

## Auth-Service
* For API-Swagger: http://localhost:9090/v2/api-docs
* Inject Opentracing Agent as aDependency (Spring Filter)
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
