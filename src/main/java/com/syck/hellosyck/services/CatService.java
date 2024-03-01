package com.syck.hellosyck.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Iterator;

@Service
public class CatService {

    @Async
    public String getCat() throws IOException {
        String apiUrl = "https://api.thecatapi.com/v1/images/search";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        System.out.println(response);
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
