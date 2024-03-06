package com.skiResortManagement;

import com.skiResortManagement.config.MultiThreadedClient.Requests10k;
import com.skiResortManagement.config.MultiThreadedClient.Threads32;
import com.skiResortManagement.model.ServerResponse;
import com.skiResortManagement.util.CsvWriter;
import com.skiResortManagement.util.Stats;

import java.util.ArrayList;
import java.util.List;

public class MultithreadedClient {
    public static void main(String[] args) {


        Threads32 threads32 = new Threads32();
        Requests10k requests10k = new Requests10k();


        double initTime1 = System.nanoTime();
        threads32.requestsFrom32Threads();
        double finalTime1 = System.nanoTime();
        double time1 = Math.round(((finalTime1 - initTime1)/1000000000)*100.0)/100.0;

        List<String> responses1 = threads32.allResponses.stream().map(ServerResponse::getResponse).toList();
        List<String> statuses1 = threads32.allResponses.stream().map(ServerResponse::getStatusCode).toList();
        List<Double> latencies1 = threads32.allResponses.stream().map(ServerResponse::getLatency).toList();

        int unsuccessfulRequests1 = 0;
        for (String status : statuses1) {
            if (!status.equals("200 OK")) {
                unsuccessfulRequests1++;
            }
        }

        int totalRequests1 = latencies1.size();
        double sum1 = Stats.calculateTotal(latencies1)/1000;
        double mean1 = Stats.calculateMean(latencies1);
        double median1 = Stats.calculateMedian(latencies1);
        double throughput1 = Stats.throughput(totalRequests1,time1);
        double p99_1 = Stats.calculate99thPercentile(latencies1);
        double min1 = Stats.findMin(latencies1);
        double max1 = Stats.findMax(latencies1);

        double initTime2 = System.nanoTime();
        requests10k.requests10k();
        double finalTime2 = System.nanoTime();
        double time2 = Math.round(((finalTime2 - initTime2)/1000000000)*100.0)/100.0;

        List<String> responses2 = requests10k.allResponses.stream().map(ServerResponse::getResponse).toList();
        List<String> statuses2 = requests10k.allResponses.stream().map(ServerResponse::getStatusCode).toList();
        List<Double> latencies2 = requests10k.allResponses.stream().map(ServerResponse::getLatency).toList();

        int unsuccessfulRequests2 = 0;
        for (String status : statuses2) {
            if (!status.equals("200 OK")) {
                unsuccessfulRequests2++;
            }
        }

        int totalRequests2 = latencies2.size();
        double sum2 = Stats.calculateTotal(latencies2)/1000.0;
        double mean2 = Stats.calculateMean(latencies2);
        double median2 = Stats.calculateMedian(latencies2);
        double throughput2 = Stats.throughput(totalRequests2,time2);
        double p99_2 = Stats.calculate99thPercentile(latencies2);
        double min2 = Stats.findMin(latencies2);
        double max2 = Stats.findMax(latencies2);

        double totalTime = time1 + time2;
        int totalRequests = totalRequests1 + totalRequests2;
        int unsucessfulRequests = unsuccessfulRequests1 + unsuccessfulRequests2;
        List<Double> total = new ArrayList<>(latencies1);
        total.addAll(latencies2);


        double sumTotal = Stats.calculateTotal(total)/1000.0;
        double meanTotal = Stats.calculateMean(total);
        double medianTotal = Stats.calculateMedian(total);
        double throughputTotal = Stats.throughput(totalRequests,totalTime);
        double p99_Total = Stats.calculate99thPercentile(total);
        double minTotal = Stats.findMin(total);
        double maxTotal = Stats.findMax(total);

        CsvWriter.writeQueueToCsv(threads32.allResponses, "responses.csv",false);
        CsvWriter.writeQueueToCsv(requests10k.allResponses, "responses.csv",true);

        System.out.println("-------------STATS------------");
        System.out.println("------------PHASE-1-----------");
        System.out.println(" Total time taken for 32k Requests: " + time1 + " s");
        System.out.println("Total Requests: "+totalRequests1);
        System.out.println("Total Unsuccessful Requests: "+unsuccessfulRequests1);
        System.out.println("Mean: "+ mean1 + " ms");
        System.out.println("Median: "+ median1 + " ms");
        System.out.println("Throughput: " + throughput1 + " rps");
        System.out.println("p99: "+ p99_1 + " ms");
        System.out.println("Fastest Response: "+ min1 + " ms");
        System.out.println("Slowest Response: "+ max1 + " ms");
        System.out.println("Projected Time without Multithreading:" + sum1 + " s");


        System.out.println("------------PHASE-2-----------");
        System.out.println(" Total time taken for 10k Requests: " + time2 + " s");
        System.out.println("Total Requests: "+totalRequests2);
        System.out.println("Total Unsuccessful Requests: "+unsuccessfulRequests2);
        System.out.println("Mean: "+ mean2 + " ms");
        System.out.println("Median: "+ median2 + " ms");
        System.out.println("Throughput: " + throughput2 + " rps");
        System.out.println("p99: "+ p99_2 + " ms");
        System.out.println("Fastest Response: "+ min2 + " ms");
        System.out.println("Slowest Response: "+ max2 + " ms");
        System.out.println("Projected Time without Multithreading:" + sum2 + " s");


        System.out.println("-------------TOTAL------------");
        System.out.println(" Total wall time: " + totalTime + " s");
        System.out.println("Total Requests: "+totalRequests);
        System.out.println("Total Unsuccessful Requests: "+unsucessfulRequests);
        System.out.println("Mean: "+ meanTotal);
        System.out.println("Median: "+ medianTotal);
        System.out.println("Throughput: " + throughputTotal + " rps");
        System.out.println("p99: "+ p99_Total + " ms");
        System.out.println("Fastest Response: "+ minTotal + " ms");
        System.out.println("Slowest Response: "+ maxTotal + " ms");
        System.out.println("Projected Time without Multithreading:" + sumTotal + " s");



    }
}
