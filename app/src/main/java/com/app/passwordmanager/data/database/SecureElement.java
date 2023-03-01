package com.app.passwordmanager.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "secureElement")
public class SecureElement {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "passcode")
    public String passcode;

    public SecureElement() {}

    public SecureElement(int id, String name, String passcode) {
        this.id = id;
        this.name = name;
        this.passcode = passcode;
    }

    @Override
    public String toString() {
        return "SecureElement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passcode='" + passcode + '\'' +
                '}';
    }
}
