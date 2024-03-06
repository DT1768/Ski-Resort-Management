package com.skiResortManagement.client;


import com.google.gson.Gson;
import com.skiResortManagement.model.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class SkiManagerClient {


    Random random = new Random();
    RequestsHTTP requestsHTTP = new RequestsHTTP();
    Gson gson = new Gson();
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ServerResponse rideEventRequest() {
        String path = "/resorts/"+ (random.nextInt(10)+1) + "/seasons/" + 2022 + "/days/" + 1 + "/skiers/" + (random.nextInt(100000)+1);
        int time = random.nextInt(360)+1;
        int liftId = random.nextInt(40)+1;

        Map<String,Object> data = new HashMap<>();
        data.put("time",time);
        data.put("liftId", liftId);
        String body = gson.toJson(data);

        double initTime = System.nanoTime();
        ResponseEntity<String> res = requestsHTTP.postRequest(body,path);
        double finalTime = System.nanoTime();
        double latency = (finalTime - initTime)/1000000;

        //logger.info(res.getBody());
        //logger.info("POST " + path + " Request Completed in " + latency + "ms");

        return new ServerResponse(initTime/1000000,finalTime/1000000,res.getStatusCode().toString(),res.getBody(),latency);
    }

    public ServerResponse getRideEvents() {
        String path = "/rideEvents";
        double initTime = System.nanoTime();
        ResponseEntity<String> res = requestsHTTP.getRequest(path);
        double finalTime = System.nanoTime();
        double latency = (finalTime - initTime)/1000000;

        //logger.info("GET " + path + " Request Completed in " + latency + "ms");

        return new ServerResponse(initTime/1000000,finalTime/1000000,res.getStatusCode().toString(),res.getBody(),latency);
    }





}
