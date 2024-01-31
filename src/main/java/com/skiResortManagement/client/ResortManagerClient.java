package com.skiResortManagement.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ResortManagerClient {

    Random random = new Random();
    RequestsHTTP requestsHTTP = new RequestsHTTP();

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
}
