package com.skiResortManagement;

import com.skiResortManagement.client.ResortManagerClient;
import com.skiResortManagement.client.SkiManagerClient;

import java.util.Random;

public class SkiResortManagementClient {
    public static void main(String[] args) {

        Random random = new Random();

        SkiManagerClient skiManagerClient = new SkiManagerClient();
        ResortManagerClient resortManagerClient = new ResortManagerClient();

        resortManagerClient.getSeasonsRequest();

        skiManagerClient.rideEventRequest();
        resortManagerClient.getResortsRequest();
        resortManagerClient.getSeasonsRequest();
    }
}
