package com.observability.authservice.service;

import com.observability.authservice.dto.RegisterRequest;
import com.observability.authservice.model.Person;
import com.observability.authservice.repository.PersonRepository;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;;
    private final ModelMapper modelMapper;
    private final io.opentracing.Tracer tracer;


    @Override
    public Person register(RegisterRequest registerRequest){
        Person person = modelMapper.map(registerRequest,Person.class);
        person.setAdmin(false);
        person.setPassword(person.getPassword());
        return personRepository.save(person);
    }

    @Override
    public Person getPerson(Long id) {
        Tracer.SpanBuilder spanBuilder = tracer.buildSpan("CustomSpan")
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER);

        Span span = spanBuilder.start();
        Tags.COMPONENT.set(span, "Auth-Service/PersonServiceImpl");
        span.setTag("testtag", "test");
        span.finish();

        return  personRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
