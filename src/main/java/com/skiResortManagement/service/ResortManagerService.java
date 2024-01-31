package com.skiResortManagement.service;

import com.google.gson.Gson;
import com.skiResortManagement.model.ResortManager;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class ResortManagerService {
    private final int maxAttempt = 5;

    ArrayList<ResortManager> listOfResorts = new ArrayList<ResortManager>();
    Gson gson = new Gson();

    public ResortManagerService() {
        listOfResorts.add(new ResortManager(1, "Sky view Resort", 2000, new ArrayList<Integer>(List.of(2022, 2023, 2024))));
        listOfResorts.add(new ResortManager(2, "Sea blue Resort", 3000, new ArrayList<Integer>(List.of(2022, 2023, 2024))));
        listOfResorts.add(new ResortManager(3, "Night vision Resort", 1500, new ArrayList<Integer>(List.of(2022, 2023, 2024))));
        listOfResorts.add(new ResortManager(4, "Sand dune Resort", 4000, new ArrayList<Integer>(List.of(2022, 2023, 2024))));
    }

    @Retryable(retryFor = ResponseStatusException.class, maxAttempts = maxAttempt, backoff = @Backoff(delay = 1000))
    public String getResorts() {
        String out = gson.toJson(listOfResorts);
        String response = "List of Resorts:\n " + out;
        return response;
    }

    @Retryable(retryFor = ResponseStatusException.class, maxAttempts = maxAttempt, backoff = @Backoff(delay = 1000))
    public String getSeasons(int id) {
        String response = "";
        String out = "";
        ArrayList<Integer> seasons = new ArrayList<Integer>();
        for (ResortManager resortManager: listOfResorts){
            if(resortManager.getResortId() == id){
                seasons = resortManager.getSeasons();
                out = gson.toJson(seasons);
            }
        }

        if (out.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't Find Resort.");
        } else {
            response = "Request Fetched Successfully." + "\n"  + "Seasons: " + out  ;
        }

        return response;
    }

    @Retryable(retryFor = ResponseStatusException.class, maxAttempts = maxAttempt, backoff = @Backoff(delay = 1000))
    public String addSeason(int id, int season) {
        String response = "";
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (ResortManager resortManager: listOfResorts){
            if(resortManager.getResortId() == id){
                out = resortManager.getSeasons();
            }
        }

        if (out.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't Find Resort.");
        }
        else if(out.isEmpty()){

        }
        else {
            //TODO: Add validation to season if it already exists
            out.add(season);
            response = "Request Posted Successfully." + "\n"  + "Seasons: " + out  ;
        }

        return response;
    }
}
