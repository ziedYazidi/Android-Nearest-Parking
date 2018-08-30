package org.openalpr.model;

import java.util.ArrayList;

public class Results {

    private final Double epoch_time;

    private final Double processing_time_ms;

    private final ArrayList<Result> results;

    public Results(Double epoch_time, Double processing_time_ms, ArrayList<Result> results) {
        this.epoch_time = epoch_time;
        this.processing_time_ms = processing_time_ms;
        this.results = results;
    }

    public Double getEpoch_time() {
        return epoch_time;
    }

    public Double getProcessing_time_ms() {
        return processing_time_ms;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

}
