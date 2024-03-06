package com.skiResortManagement.client;

import com.google.gson.Gson;
import com.skiResortManagement.model.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ResortManagerClient {

    Random random = new Random();
    RequestsHTTP requestsHTTP = new RequestsHTTP();

    Gson gson = new Gson();

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    public ServerResponse getResortsRequest() {
        String path = "/resorts";
        double initTime = System.nanoTime();
        ResponseEntity<String> res = requestsHTTP.getRequest(path);
        double finalTime = System.nanoTime();
        double latency = (finalTime - initTime)/1000000;
        //logger.info(res);
        //logger.info("GET " + path + " Request Completed in " + latency + "ms");

        return new ServerResponse(initTime/1000000,finalTime/1000000,res.getStatusCode().toString(), res.getBody(), latency);
    }

    public ServerResponse getSeasonsRequest(){
        String path = "/resorts/" + (random.nextInt(4)+1) + "/seasons";
        double initTime = System.nanoTime();
        ResponseEntity<String> res = requestsHTTP.getRequest(path);
        double finalTime = System.nanoTime();
        double latency = (finalTime - initTime)/1000000;
        //ogger.info(res);
        //logger.info("GET " + path + " Request Completed in " + latency + "ms");

        return new ServerResponse(initTime/1000000,finalTime/1000000,res.getStatusCode().toString(), res.getBody(), latency);
    }

    public ServerResponse addSeasonRequest(){
        String path = "/resorts/" + (random.nextInt(4)+1) + "/seasons";
        int season = random.nextInt(2030-2025+1)+2025;

        Map<String,Object> data = new HashMap<>();
        data.put("season",season);
        String body = gson.toJson(data);

        double initTime = System.nanoTime();
        ResponseEntity<String> res = requestsHTTP.postRequest(body,path);
        double finalTime = System.nanoTime();
        double latency = (finalTime - initTime)/1000000;
        System.out.println(res.getStatusCode());
        System.out.println(res.getBody());
        System.out.println(res.getHeaders());
        //logger.info(res.getBody());
        //logger.info("POST " + path + " Request Completed in " + latency + "ms");

        return new ServerResponse(initTime/1000000,finalTime/1000000,res.getStatusCode().toString(), res.getBody(), latency);
    }

}
