package com.maliblo.fincam.Db.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bills")
public class Bills {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String uri;

    private String timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Bills(String uri, String timeStamp) {
        this.uri = uri;
        this.timeStamp = timeStamp;
    }
}
