package com.skiResortManagement.config.MultiThreadedClient;

import com.skiResortManagement.client.SkiManagerClient;
import com.skiResortManagement.model.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class Threads32 {
    int threads = 32;
    int requestPerThread = 1000;

    public Queue<ServerResponse> allResponses = new ConcurrentLinkedQueue<>();

    ExecutorService executorService = Executors.newFixedThreadPool(threads);

    public void requestsFrom32Threads(){
        List<Future<Void>> futures = new ArrayList<>();
        for(int i=0;i<threads;i++){

            EvenRequestSender requestSender = new EvenRequestSender("Thread-" + i, requestPerThread);
            Future<Void> future = executorService.submit(requestSender);
            futures.add(future);
        }

        executorService.shutdown();

        try {
            // Waits for all threads to complete execution after shutdown
            executorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);

            for (Future<Void> future : futures) {
                future.get(); // This will rethrow any exceptions that occurred during execution
            }
        } catch (InterruptedException | ExecutionException e) {
            // Handle interruption if needed
            e.printStackTrace();
        }
    }

    private class EvenRequestSender implements Callable<Void> {

        SkiManagerClient skiManagerClient = new SkiManagerClient();

        private final String threadName;
        private final int requestPerThread;

        public EvenRequestSender(String threadName, int requestPerThread){
            this.threadName = threadName;
            this.requestPerThread = requestPerThread;
        }

        @Override
        public Void call() throws Exception {
            try{
                for (int j = 0; j < requestPerThread; j++) {
                    allResponses.add(skiManagerClient.rideEventRequest());
                }
                return null;
            }
            catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
    }
}
