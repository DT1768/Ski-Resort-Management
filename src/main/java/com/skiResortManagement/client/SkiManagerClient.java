package com.skiResortManagement.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;


public class SkiManagerClient {


    Random random = new Random();
    RequestsHTTP requestsHTTP = new RequestsHTTP();

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void rideEventRequest() {
        String path = "/resorts/"+ (random.nextInt(10)+1) + "/seasons/" + 2022 + "/days/" + 1 + "/skiers/" + (random.nextInt(100000)+1);
        double initTime = System.nanoTime();
        String res = requestsHTTP.postRequest(path).getBody();
        double finalTime = System.nanoTime();
        double time = finalTime - initTime;
        logger.info(String.valueOf(res));
        logger.info("POST " + path + " Request Completed in " + time/1000000 + "ms");
    }




}
