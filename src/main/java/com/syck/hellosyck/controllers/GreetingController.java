package com.syck.hellosyck.controllers;

import com.syck.hellosyck.models.Greeting;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting greeting(@PathVariable("name") String name){
        System.out.println("In greeting method, name is --> " + name);
                
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

}
