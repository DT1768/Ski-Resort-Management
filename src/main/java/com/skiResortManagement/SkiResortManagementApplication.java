package com.skiResortManagement;

import com.skiResortManagement.client.SkiManagerClient;
import com.skiResortManagement.model.SkiManager;
import com.skiResortManagement.service.SkiManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SkiResortManagementApplication {
    public static void main(String[] args) {

        SpringApplication.run(SkiResortManagementApplication.class, args);


    }

    @Autowired
    private SkiManagerService skimanagerservice;

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/rideEvent")
    public String newSkimanager(@RequestBody SkiManager newSkimanager){
        String response = skimanagerservice.createRideEvent(newSkimanager);

        return response;
    }

}
