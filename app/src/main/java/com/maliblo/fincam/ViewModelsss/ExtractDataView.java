package com.maliblo.fincam.ViewModelsss;

import com.maliblo.fincam.Db.tables.ExtractedData;

import java.util.List;

public class ExtractDataView {
    private int logo;
    private String companyName;
    private String total;
    private List<ExtractedData> extractedData;


    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTotal() {
        return total;
    }

    public List<ExtractedData> getExtractedData() {
        return extractedData;
    }

    public void setExtractedData(List<ExtractedData> extractedData) {
        this.extractedData = extractedData;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ExtractDataView(int logo, String companyName, String total, List<ExtractedData> extractedData) {
        this.logo = logo;
        this.companyName = companyName;
        this.total = total;
        this.extractedData = extractedData;
    }

}
