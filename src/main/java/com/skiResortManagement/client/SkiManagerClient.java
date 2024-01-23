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

    private static final String URL = "http://localhost:8080/rideEvent";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void rideEventRequest() {
        String rideEvent = gson.toJson(generateRideEvent());
        double initTime = System.nanoTime();
        String res = postRequest(rideEvent).getBody();
        double finalTime = System.nanoTime();
        double time = finalTime - initTime;
        logger.info(String.valueOf(res));
        logger.info("Single Request Completed in " + time/1000000 + "ms");
    }



    public SkiManager generateRideEvent(){
        return new SkiManager(random.nextInt(100000)+1,random.nextInt(10)+1,random.nextInt(40)+1,2022,1,random.nextInt(360)+1);
    }

    public ResponseEntity<String> postRequest(String e){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(e, headers);

        ResponseEntity<String> res = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

        return res;
    }
}
