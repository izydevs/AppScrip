package com.appscrip.amit.triviaapp.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "game")
public class Game implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "time")
    private long time;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "q1ans")
    private String q1Ans;

    @ColumnInfo(name = "q2ans")
    private String q2Ans;

    public Game(long time, String name, String q1Ans, String q2Ans) {
        this.time = time;
        this.name = name;
        this.q1Ans = q1Ans;
        this.q2Ans = q2Ans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQ1Ans() {
        return q1Ans;
    }

    public void setQ1Ans(String q1Ans) {
        this.q1Ans = q1Ans;
    }

    public String getQ2Ans() {
        return q2Ans;
    }

    public void setQ2Ans(String q2Ans) {
        this.q2Ans = q2Ans;
    }
}
