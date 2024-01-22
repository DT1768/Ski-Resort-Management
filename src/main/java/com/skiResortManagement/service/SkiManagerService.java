package com.skiResortManagement.service;

import com.skiResortManagement.model.SkiManager;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SkiManagerService {
    private final int maxAttempt = 5;

    @Retryable(retryFor = ResponseStatusException.class, maxAttempts = maxAttempt, backoff = @Backoff(delay = 1000))
    public String createRideEvent(SkiManager newSkiManager){
        String response = "";
        boolean validRequest = requestValidator(newSkiManager);
        if(!validRequest){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
        }
        else{
            response = "Request Posted Successfully. Details: SKierId:" + newSkiManager.getSkierId() + " LiftId:"+ newSkiManager.getLiftId() + " ResortId:" + newSkiManager.getResortId();
        }
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
