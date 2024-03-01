package com.syck.hellosyck.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.syck.hellosyck.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
public class CatController {

    @Autowired
    private CatService catService;

    @RequestMapping(value = "/getcat", method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ResponseBody
    public String getCat() throws Exception{
        return catService.getCat();
    }

}
