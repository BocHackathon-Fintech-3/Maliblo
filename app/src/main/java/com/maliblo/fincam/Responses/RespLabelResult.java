package com.maliblo.fincam.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespLabelResult {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("input")
    @Expose
    private String input;
    @SerializedName("prediction")
    @Expose
    private List<RespLabelPrediction> prediction;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setPrediction(List<RespLabelPrediction> prediction) {
        this.prediction = prediction;
    }

    public String getMessage() {
        return message;
    }

    public String getInput() {
        return input;
    }

    public List<RespLabelPrediction> getPrediction() {
        return prediction;
    }
}
