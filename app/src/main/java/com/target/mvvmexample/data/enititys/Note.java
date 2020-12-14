package com.target.mvvmexample.data.enititys;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "notes_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String body;

    public Note(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
