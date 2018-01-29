package com.syck.hellosyck.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.syck.hellosyck.models.ImageType;
import com.syck.hellosyck.models.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
    public class CatController {


    @RequestMapping(value = "/getcat", method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ResponseBody
    public String getCat() throws Exception{

        String apiUrl = "http://thecatapi.com/api/images/get?format=xml&results_per_page=1";

        RestTemplate restTemplate = new RestTemplate();

        ResponseType resp = restTemplate.getForObject(apiUrl, ResponseType.class);

        System.out.println("Response");
        String imageUrl = "";

        for(ImageType image : resp.getData().getImages().getImage()){
            System.out.println(image.getUrl());
            imageUrl = image.getUrl();
        }

        return "{\"img\":\"" + imageUrl + "\"}";
    }
}
