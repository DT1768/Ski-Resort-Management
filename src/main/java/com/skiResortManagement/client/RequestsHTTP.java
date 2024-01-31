package com.skiResortManagement.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RequestsHTTP {


    private RestTemplate restTemplate = new RestTemplate();

    private static final String URL = "http://localhost:8080";

    public ResponseEntity<String> postRequest(String e,String path){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(e,headers);

        ResponseEntity<String> res = restTemplate.exchange(URL + path, HttpMethod.POST, entity, String.class);

        return res;
    }

    public ResponseEntity<String> getRequest(String path){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> res = restTemplate.exchange(URL + path, HttpMethod.GET, entity, String.class);

        return res;
    }
}
