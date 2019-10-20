package com.maliblo.fincam.Db.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tags")
public class Tags {
    @PrimaryKey(autoGenerate = true)
    int id;
    String tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Tags(String tags) {
        this.tags = tags;
    }
}
