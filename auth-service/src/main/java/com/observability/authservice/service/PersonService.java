package com.observability.authservice.service;


import com.observability.authservice.dto.RegisterRequest;
import com.observability.authservice.model.Person;

public interface PersonService {

    Person register(RegisterRequest registerRequest);
    Person getPerson(Long id);
    void delete(Long id);
}
