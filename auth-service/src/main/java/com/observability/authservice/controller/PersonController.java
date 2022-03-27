package com.observability.authservice.controller;


import com.observability.authservice.dto.RegisterRequest;
import com.observability.authservice.model.Person;
import com.observability.authservice.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1.0/person")
@CrossOrigin
@RequiredArgsConstructor
@Api(value = "Person Api documentation")
public class PersonController {

    private final PersonService personService;
    private final RestTemplate restTemplate;

    @CachePut(value = "people")
    @PostMapping("/register")
    @ApiOperation(value = "New Person adding method")
    public Person createUser(@Valid @RequestBody RegisterRequest registerRequest){
        try{
            Person person = personService.register(registerRequest);
            System.out.println(person.getId());
            return person;
        }catch (Exception e){
            return null;
        }
    }

    @Cacheable(value = "people", key = "#id")
    @GetMapping("/{id}")
    @ApiOperation(value = "Get person method")
    public Person getPerson(@PathVariable("id") Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("Getting user with ID" + id, headers);
        restTemplate.exchange("http://print-service:9091/api/v1.0/print", HttpMethod.POST, entity , String.class);
        return personService.getPerson(id);
    }

    @CacheEvict(value = "people", allEntries=true)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete person method")
    public void deletePersonById(@PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("Deleting person with id "+ id, headers);
        restTemplate.exchange("http://print-service:9091/api/v1.0/print", HttpMethod.POST, entity , String.class);
        personService.delete(id);
    }

}
