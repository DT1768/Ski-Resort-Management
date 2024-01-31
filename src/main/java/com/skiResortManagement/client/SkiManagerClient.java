package com.skiResortManagement.client;

import com.google.gson.Gson;
import com.skiResortManagement.model.SkiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;


public class SkiManagerClient {
    private Gson gson = new Gson();
    private RestTemplate restTemplate = new RestTemplate();
    Random random = new Random();

    private static final String URL = "http://localhost:8080";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void rideEventRequest() {
        String path = "/resorts/"+ (random.nextInt(10)+1) + "/seasons/" + 2022 + "/days/" + 1 + "/skiers/" + (random.nextInt(100000)+1);
        double initTime = System.nanoTime();
        String res = postRequest(path).getBody();
        double finalTime = System.nanoTime();
        double time = finalTime - initTime;
        logger.info(String.valueOf(res));
        logger.info("POST " + path + "Request Completed in " + time/1000000 + "ms");
    }

    public void getResortsRequest() {
        String path = "/resorts";
        double initTime = System.nanoTime();
        String res = getRequest(path).getBody();
        double finalTime = System.nanoTime();
        double time = finalTime - initTime;
        logger.info(String.valueOf(res));
        logger.info("GET " + path + " Request Completed in " + time/1000000 + "ms");
    }

    public void getSeasonsRequest(){
        String path = "/resorts/" + (random.nextInt(4)+1) + "/seasons";
        double initTime = System.nanoTime();
        String res = getRequest(path).getBody();
        double finalTime = System.nanoTime();
        double time = finalTime - initTime;
        logger.info(String.valueOf(res));
        logger.info("GET " + path + " Request Completed in " + time/1000000 + "ms");
    }

    public ResponseEntity<String> postRequest(String path){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

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
