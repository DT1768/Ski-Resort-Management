package com.skiResortManagement;

import com.skiResortManagement.client.SkiManagerClient;

public class SkiResortManagementClient {
    public static void main(String[] args) {
        SkiManagerClient skiManagerClient = new SkiManagerClient();
        skiManagerClient.getResortsRequest();

        skiManagerClient.rideEventRequest();
        skiManagerClient.getResortsRequest();
    }
}
