package com.skiResortManagement.model;

public class ServerResponse {
    private Double startTime;
    private Double endTime;
    private String statusCode;
    private String response;
    private Double latency;

    public ServerResponse(){

    }

    public ServerResponse(Double startTime, Double endTime,String statusCode,String response, Double latency){
        this.startTime = startTime;
        this.endTime = endTime;
        this.statusCode = statusCode;
        this.response = response;
        this.latency = latency;
    }

    public Double getStartTime() {
        return startTime;
    }

    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    public Double getEndTime() {
        return endTime;
    }

    public void setEndTime(Double endTime) {
        this.endTime = endTime;
    }

    public String getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Double getLatency(){
        return latency;
    }

    public void setLatency(Double latency) {
        this.latency = latency;
    }
}
