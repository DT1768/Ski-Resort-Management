package com.skiResortManagement.config.MultiThreadedClient;

import com.skiResortManagement.client.SkiManagerClient;
import com.skiResortManagement.model.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

//public class Requests10k {
//
//    int totalRequests = 10000;
//    int threads = 30;
//
//    public Queue<ServerResponse> allResponses = new ConcurrentLinkedQueue<>();
//
//    ExecutorService executorService = Executors.newFixedThreadPool(threads);
//
//    public void requests10k(){
//
//        int remainingRequests = totalRequests;
//        List<Future<Void>> futures = new ArrayList<>();
//
//        while (remainingRequests>0){
//            int requestPerThread = (int) Math.ceil((double) remainingRequests/threads);
//            //System.out.println(futures.size());
//            RequestSender requestSender = new RequestSender("Thread-" + futures.size(), requestPerThread);
//            Future<Void> future = executorService.submit(requestSender);
//            futures.add(future);
//            remainingRequests = remainingRequests - requestPerThread;
//        }
//
//        executorService.shutdown();
//
//        try {
//            // Waits for all threads to complete execution after shutdown
//            executorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
//
//            for (Future<Void> future : futures) {
//                future.get(); // This will rethrow any exceptions that occurred during execution
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            // Handle interruption if needed
//            e.printStackTrace();
//        }
//    }
//
//    private class RequestSender implements Callable<Void> {
//
//        SkiManagerClient skiManagerClient = new SkiManagerClient();
//
//        private final String threadName;
//        private final int targetRequests;
//
//        public RequestSender(String threadName,int targetRequests){
//            this.threadName = threadName;
//            this.targetRequests = targetRequests;
//        }
//
//        @Override
//        public Void call() throws Exception {
//            try{
//                int sentRequest = 0;
//                while (sentRequest < targetRequests) {
//                    allResponses.add(skiManagerClient.rideEventRequest());
//                    sentRequest++;
//                }
//                return null;
//            }
//            catch (Exception e){
//                e.printStackTrace();
//                throw e;
//            }
//        }
//    }
//}
//
//
//package com.skiResortManagement.config.MultiThreadedClient;
//
//        import com.skiResortManagement.client.SkiManagerClient;
//        import com.skiResortManagement.model.ServerResponse;
//
//        import java.util.ArrayList;
//        import java.util.List;
//        import java.util.concurrent.*;

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