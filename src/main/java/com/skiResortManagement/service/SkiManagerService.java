package com.skiResortManagement.service;


import com.google.gson.Gson;
import com.skiResortManagement.model.SkiManager;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;


@Service
public class SkiManagerService {
    private final int maxAttempt = 5;

    ArrayList<SkiManager> rideList = new ArrayList<SkiManager>();

    public SkiManagerService(){

        rideList.add(new SkiManager(1,1,1,2022,1,1));
    }

    Gson gson = new Gson();
    @Retryable(retryFor = ResponseStatusException.class, maxAttempts = maxAttempt, backoff = @Backoff(delay = 1000))
    public String createRideEvent(SkiManager newSkiManager){
        String response = "";
        boolean validRequest = requestValidator(newSkiManager);
        if(!validRequest){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
        }
        else{
            java.util.Map<String, Object> data = new java.util.HashMap<>();
            data.put("SkierId",newSkiManager.getSkierId());
            data.put("ResortId",newSkiManager.getResortId());
            data.put("LiftId",newSkiManager.getLiftId());
            data.put("seasonId", newSkiManager.getSeasonId());
            data.put("dayId",newSkiManager.getDayId());
            data.put("time",newSkiManager.getTime());
            String out = gson.toJson(data);
            response = "Request Fetched Successfully." +"Details:" + out;
            rideList.add(newSkiManager);
        }
        return response;
    }

    @Retryable(retryFor = ResponseStatusException.class, maxAttempts = maxAttempt, backoff = @Backoff(delay = 1000))
    public String getRideEvents(){
        String out = gson.toJson(rideList);
        String response = "List of Ski Rides: " + out;
        return response;
    }



    private boolean requestValidator(SkiManager newSkimanager){
        boolean validSkier = newSkimanager.getSkierId() >= 1 && newSkimanager.getSkierId() <= 100000;
        boolean validResort = newSkimanager.getResortId() >= 1 && newSkimanager.getResortId() <= 10;
        boolean validLift = newSkimanager.getLiftId() >= 1 && newSkimanager.getLiftId() <= 40;
        boolean validSeason = (newSkimanager.getSeasonId() == 2022);
        boolean validDay =  (newSkimanager.getDayId() == 1);
        boolean validTime = newSkimanager.getTime() >= 1 && newSkimanager.getTime() <= 360;

        return validSkier && validResort && validLift && validSeason && validDay && validTime;
    }
}
