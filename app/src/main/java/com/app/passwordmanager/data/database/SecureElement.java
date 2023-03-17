package com.app.passwordmanager.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "secureElement",
        indices = {@Index(value = {"id"}, unique = true)})
public class SecureElement implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "notes")
    public String notes;

    public SecureElement() {
    }

    public SecureElement(int id, String title, String password, String notes) {
        this.id = id;
        this.title = title;
        this.password = password;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "SecureElement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", password='" + password + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
