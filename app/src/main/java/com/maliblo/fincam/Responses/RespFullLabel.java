package com.maliblo.fincam.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespFullLabel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<RespLabelResult> result;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(List<RespLabelResult> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public List<RespLabelResult> getResult() {
        return result;
    }
}
//{"message":"Success","result":[{"message":"Success","input":"IMG_20191018_141405.jpg","prediction":[{"label":"total","xmin":2604,"ymin":3502,"xmax":2811,"ymax":3570,"score":0.959715,"ocr_text":"30.49\n"},{"label":"invoice_no","xmin":1746,"ymin":3778,"xmax":2118,"ymax":3847,"score":0.75919896,"ocr_text":"ОКТ. ДЕК 2014\n"},{"label":"company_name","xmin":1075,"ymin":3536,"xmax":1577,"ymax":3610,"score":0.60425353,"ocr_text":"| 1275090 1505800 W\n"},{"label":"invoice_no","xmin":1774,"ymin":3335,"xmax":2221,"ymax":3403,"score":0.5282778,"ocr_text":"ΡΙΟΜΟΣ ΛΟΓΑΡΙΑΣΜΟΥ\nA TOADEZIKN ENTOAN\n"}]}]}