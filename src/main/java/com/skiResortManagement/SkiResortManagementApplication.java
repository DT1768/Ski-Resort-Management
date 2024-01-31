package com.skiResortManagement;

import com.skiResortManagement.client.SkiManagerClient;
import com.skiResortManagement.model.SkiManager;
import com.skiResortManagement.service.ResortManagerService;
import com.skiResortManagement.service.SkiManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@SpringBootApplication
@RestController
public class SkiResortManagementApplication {
    public static void main(String[] args) {

        SpringApplication.run(SkiResortManagementApplication.class, args);

    }

    @Autowired
    private SkiManagerService skimanagerservice;
    private ResortManagerService resortManagerService = new ResortManagerService();

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/resorts/{resortId}/seasons/{seasonId}/days/{dayId}/skiers/{skierId}")
    public String postRideEvent(@PathVariable Map<String, String> pathVariables){

        Random random = new Random();
        int resortId = Integer.parseInt(pathVariables.get("resortId"));
        int seasonId = Integer.parseInt(pathVariables.get("seasonId"));
        int dayId = Integer.parseInt(pathVariables.get("dayId"));
        int skierId = Integer.parseInt(pathVariables.get("skierId"));
        int liftId = random.nextInt(40)+1;
        int time = random.nextInt(360)+1;
        SkiManager newSkimanager = new SkiManager(skierId, resortId, liftId, seasonId, dayId, time);

        String response = skimanagerservice.createRideEvent(newSkimanager);

        return response;
    }

    @GetMapping("/resorts")
    public String getResorts(){
        String response = resortManagerService.getResorts();

        return response;
    }

    @GetMapping("/resorts/{resortId}/seasons")
    public String getSeasons(@PathVariable String resortId){
        int id = Integer.parseInt(resortId);

        String response = resortManagerService.getSeasons(id);

        return response;
    }

}
