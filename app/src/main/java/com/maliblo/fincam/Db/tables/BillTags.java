package com.maliblo.fincam.Db.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "bill_tags")
@ForeignKey(entity = Tags.class, parentColumns = "id", childColumns = "billId,tagId")
public class BillTags {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int billId;

    private int tagId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public BillTags(int billId, int tagId) {

        this.billId = billId;
        this.tagId = tagId;
    }
}
