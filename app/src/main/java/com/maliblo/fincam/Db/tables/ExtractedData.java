package com.maliblo.fincam.Db.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "extractedData")
public class ExtractedData {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int bill_id;

    private String invoiceNo;

    private String company;

    private String total;

    private String invoiceCheckNo;

    private String barcode;

    public ExtractedData(int bill_id, String company, String invoiceNo, String total, String invoiceCheckNo, String barcode) {
        this.bill_id = bill_id;
        this.invoiceNo = invoiceNo;
        this.company = company;
        this.total = total;
        this.invoiceCheckNo = invoiceCheckNo;
        this.barcode = barcode;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getInvoiceCheckNo() {
        return invoiceCheckNo;
    }

    public void setInvoiceCheckNo(String invoiceCheckNo) {
        this.invoiceCheckNo = invoiceCheckNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
