package com.example.rest_api.controller;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.rest_api.model.Greeting;
import com.example.rest_api.model.HypermediaGreeting;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/hypermedia_greeting")
    public HttpEntity<HypermediaGreeting> hypermediaGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        HypermediaGreeting hypermediaGreeting = new HypermediaGreeting(String.format(template, name));
        hypermediaGreeting.add(linkTo(methodOn(GreetingController.class).hypermediaGreeting(name)).withSelfRel());

        return new ResponseEntity<>(hypermediaGreeting, HttpStatus.OK);
    }
}
