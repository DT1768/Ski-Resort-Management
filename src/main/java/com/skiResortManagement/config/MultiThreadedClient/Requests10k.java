package com.skiResortManagement.config.MultiThreadedClient;

import com.skiResortManagement.client.SkiManagerClient;
import com.skiResortManagement.model.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class Requests10k {

    private final int totalRequests = 10000;
    private final int threads = 30;
    private final ExecutorService executorService = Executors.newFixedThreadPool(threads);
    private final BlockingQueue<Integer> requestQueue = new LinkedBlockingQueue<>();
    public ConcurrentLinkedQueue<ServerResponse> allResponses = new ConcurrentLinkedQueue<>();

    public Requests10k() {
        for (int i = 0; i < totalRequests; i++) {
            requestQueue.offer(i); // Populate the queue with request placeholders
        }
    }

    public void requests10k() {
        List<Future<Void>> futures = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            futures.add(executorService.submit(new RequestSender("Thread-" + i, requestQueue, allResponses)));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            for (Future<Void> future : futures) {
                try {
                    future.get(); // Handle exceptions and ensure completion
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static class RequestSender implements Callable<Void> {
        private final BlockingQueue<Integer> requestQueue;
        private final ConcurrentLinkedQueue<ServerResponse> allResponses;
        private final SkiManagerClient skiManagerClient = new SkiManagerClient();

        public RequestSender(String threadName, BlockingQueue<Integer> requestQueue,
                             ConcurrentLinkedQueue<ServerResponse> allResponses) {
            this.requestQueue = requestQueue;
            this.allResponses = allResponses;
        }

        @Override
        public Void call() {
            Integer requestId;
            while ((requestId = requestQueue.poll()) != null) {
                ServerResponse response = skiManagerClient.rideEventRequest();
                allResponses.add(response);
            }
            return null;
        }
    }
}