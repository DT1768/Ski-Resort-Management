package com.skiResortManagement;

import com.skiResortManagement.client.ResortManagerClient;
import com.skiResortManagement.client.SkiManagerClient;
import com.skiResortManagement.model.ServerResponse;
import org.apache.catalina.Server;

public class SkiResortManagementClient {
    public static void main(String[] args) {

        SkiManagerClient skiManagerClient = new SkiManagerClient();
        ResortManagerClient resortManagerClient = new ResortManagerClient();

        resortManagerClient.getSeasonsRequest();

        ServerResponse res = skiManagerClient.rideEventRequest();
        resortManagerClient.getResortsRequest();
        resortManagerClient.getSeasonsRequest();
        resortManagerClient.addSeasonRequest();

        skiManagerClient.getRideEvents();
    }
}
