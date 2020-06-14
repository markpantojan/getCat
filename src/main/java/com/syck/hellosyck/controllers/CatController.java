package com.syck.hellosyck.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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


    @RequestMapping(value = "/getcat", method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ResponseBody
    public String getCat() throws Exception{

        String apiUrl = "https://api.thecatapi.com/v1/images/search";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        return "{\"img\":\"" + findURL(root) + "\"}";
    }

    private static String findURL(JsonNode root) {
        if(root.isObject()){
            Iterator<String> fieldNames = root.fieldNames();

            while(fieldNames.hasNext()){
                String fieldName = fieldNames.next();
                JsonNode fieldValue = root.get(fieldName);
                return findURL(fieldValue);
            }
        } else if(root.isArray()){
            ArrayNode arrayNode = (ArrayNode) root;
            System.out.println(root.size());
            for(int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
                return arrayNode.get(i).path("url").asText();
            }
        }

        return root.path("url").asText();

    }
}
