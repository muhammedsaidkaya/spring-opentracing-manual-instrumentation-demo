package com.observability.printservice.controller;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/print")
@CrossOrigin
@RequiredArgsConstructor
public class PrintController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final io.opentracing.Tracer tracer;

    @PostMapping
    public void print(@RequestBody  String message){
        LOG.info("Getting message: {}", message);
        Tracer.SpanBuilder spanBuilder = tracer.buildSpan("CustomSpan-Print-Service")
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER);

        Span span = spanBuilder.start();
        Tags.COMPONENT.set(span, "Print-Service/PrintController");
        span.setTag("testtag", "test");
        span.finish();
    }
}
