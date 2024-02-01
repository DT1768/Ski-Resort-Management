package com.skiResortManagement;


import com.google.gson.Gson;
import com.skiResortManagement.model.SkiManager;
import com.skiResortManagement.service.ResortManagerService;
import com.skiResortManagement.service.SkiManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@SpringBootApplication
@RestController
public class SkiResortManagementApplication {
    public static void main(String[] args) {

        SpringApplication.run(SkiResortManagementApplication.class, args);

    }

    Gson gson = new Gson();

    @Autowired
    private SkiManagerService skimanagerservice = new SkiManagerService();
    private ResortManagerService resortManagerService = new ResortManagerService();

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/resorts/{resortId}/seasons/{seasonId}/days/{dayId}/skiers/{skierId}")
    public String postRideEvent(@PathVariable Map<String, String> pathVariables, @RequestBody String body){

        int resortId = Integer.parseInt(pathVariables.get("resortId"));
        int seasonId = Integer.parseInt(pathVariables.get("seasonId"));
        int dayId = Integer.parseInt(pathVariables.get("dayId"));
        int skierId = Integer.parseInt(pathVariables.get("skierId"));

        Map<String , Object> mapData = gson.fromJson(body, Map.class);

        int liftId = ((Number) mapData.get("liftId")).intValue();
        int time = ((Number) mapData.get("time")).intValue();

        SkiManager newSkimanager = new SkiManager(skierId, resortId, liftId, seasonId, dayId, time);

        String response = skimanagerservice.createRideEvent(newSkimanager);

        return response;
    }

    @GetMapping("/rideEvents")
    public String getSkiRides(){
        String response = skimanagerservice.getRideEvents();

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

    @PostMapping("/resorts/{resortId}/seasons")
    public String addSeason(@PathVariable String resortId, @RequestBody String body){
        int id = Integer.parseInt(resortId);

        Map<String , Object> data = gson.fromJson(body, Map.class);

        int season = ((Number) data.get("season")).intValue();

        String response = resortManagerService.addSeason(id,season);

        return response;
    }

}
