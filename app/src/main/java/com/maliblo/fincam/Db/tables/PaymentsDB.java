package com.maliblo.fincam.Db.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "payments")
public class PaymentsDB {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int bill_id;
    private String status;
    private String accountNo;

    public PaymentsDB(int bill_id, String status, String accountNo) {
        this.bill_id = bill_id;
        this.status = status;
        this.accountNo = accountNo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
