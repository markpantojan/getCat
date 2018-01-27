package com.syck.hellosyck.controllers;

import com.syck.hellosyck.models.Greeting;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting greeting(@PathVariable("name") String name){
        System.out.println("In greeting method, name is --> " + name);
        System.out.println("Checking if Heroku is pulling from git");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

}
