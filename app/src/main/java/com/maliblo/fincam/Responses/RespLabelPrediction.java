package com.maliblo.fincam.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespLabelPrediction {
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("xmin")
    @Expose
    private Integer xmin;
    @SerializedName("ymin")
    @Expose
    private Integer ymin;
    @SerializedName("xmax")
    @Expose
    private Integer xmax;
    @SerializedName("ymax")
    @Expose
    private Integer ymax;
    @SerializedName("score")
    @Expose
    private Float score;
    @SerializedName("ocr_text")
    @Expose
    private String ocr_text;

    public void setLabel(String label) {
        this.label = label;
    }

    public void setXmin(Integer xmin) {
        this.xmin = xmin;
    }

    public void setYmin(Integer ymin) {
        this.ymin = ymin;
    }

    public void setXmax(Integer xmax) {
        this.xmax = xmax;
    }

    public void setYmax(Integer ymax) {
        this.ymax = ymax;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public void setOcr_text(String ocr_text) {
        this.ocr_text = ocr_text;
    }

    public String getLabel() {
        return label;
    }

    public Integer getXmin() {
        return xmin;
    }

    public Integer getYmin() {
        return ymin;
    }

    public Integer getXmax() {
        return xmax;
    }

    public Integer getYmax() {
        return ymax;
    }

    public Float getScore() {
        return score;
    }

    public String getOcr_text() {
        return ocr_text;
    }
}
