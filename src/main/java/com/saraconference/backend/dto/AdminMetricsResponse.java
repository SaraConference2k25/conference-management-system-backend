package com.saraconference.backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminMetricsResponse {
    private long totalPapers;
    private long totalEvaluators;
    private long totalAccepted;
    private long totalRejected;
    private long totalAssigned;
    private long totalPending;

    public AdminMetricsResponse(){
        //empty method for deserialization
    }


}
