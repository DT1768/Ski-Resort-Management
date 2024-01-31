package com.skiResortManagement.client;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ResortManagerClient {

    Random random = new Random();
    RequestsHTTP requestsHTTP = new RequestsHTTP();

    Gson gson = new Gson();

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void getResortsRequest() {
        String path = "/resorts";
        double initTime = System.nanoTime();
        String res = requestsHTTP.getRequest(path).getBody();
        double finalTime = System.nanoTime();
        double time = finalTime - initTime;
        logger.info(String.valueOf(res));
        logger.info("GET " + path + " Request Completed in " + time/1000000 + "ms");
    }

    public void getSeasonsRequest(){
        String path = "/resorts/" + (random.nextInt(4)+1) + "/seasons";
        double initTime = System.nanoTime();
        String res = requestsHTTP.getRequest(path).getBody();
        double finalTime = System.nanoTime();
        double time = finalTime - initTime;
        logger.info(String.valueOf(res));
        logger.info("GET " + path + " Request Completed in " + time/1000000 + "ms");
    }

    public void addSeasonRequest(){
        String path = "/resorts/" + (random.nextInt(4)+1) + "/seasons";
        int season = random.nextInt(2030-2025+1)+2025;

        Map<String,Object> data = new HashMap<>();
        data.put("season",season);
        String body = gson.toJson(data);

        double initTime = System.nanoTime();
        String res = requestsHTTP.postRequest(body,path).getBody();
        double finalTime = System.nanoTime();
        double time = finalTime - initTime;
        logger.info(String.valueOf(res));
        logger.info("POST " + path + " Request Completed in " + time/1000000 + "ms");
    }

}
