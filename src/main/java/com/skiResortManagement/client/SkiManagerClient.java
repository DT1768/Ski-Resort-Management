package com.skiResortManagement.client;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class SkiManagerClient {


    Random random = new Random();
    RequestsHTTP requestsHTTP = new RequestsHTTP();
    Gson gson = new Gson();
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void rideEventRequest() {
        String path = "/resorts/"+ (random.nextInt(10)+1) + "/seasons/" + 2022 + "/days/" + 1 + "/skiers/" + (random.nextInt(100000)+1);
        int time = random.nextInt(360)+1;
        int liftId = random.nextInt(40)+1;

        Map<String,Object> data = new HashMap<>();
        data.put("time",time);
        data.put("liftId", liftId);
        String body = gson.toJson(data);


        double initTime = System.nanoTime();
        String res = requestsHTTP.postRequest(body,path).getBody();
        double finalTime = System.nanoTime();
        double time2 = finalTime - initTime;
        logger.info(res);
        logger.info("POST " + path + " Request Completed in " + time2/1000000 + "ms");
    }

    public void getRideEvents() {
        String path = "/rideEvents";
        double initTime = System.nanoTime();
        String res = requestsHTTP.getRequest(path).getBody();
        double finalTime = System.nanoTime();
        double time = finalTime - initTime;
        logger.info(res);
        logger.info("GET " + path + " Request Completed in " + time/1000000 + "ms");
    }





}
