package com.skiResortManagement;

import com.skiResortManagement.client.SkiManagerClient;

import java.util.Random;

public class SkiResortManagementClient {
    public static void main(String[] args) {
        Random random = new Random();

        SkiManagerClient skiManagerClient = new SkiManagerClient();
        skiManagerClient.getResortsRequest();

        skiManagerClient.rideEventRequest();
        skiManagerClient.getResortsRequest();
        skiManagerClient.getSeasonsRequest();
    }
}
