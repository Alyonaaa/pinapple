package com.example.pinapple_.model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int _id;

    @ColumnInfo(name = "date")
    @NonNull
    public String date;

    @ColumnInfo(name = "subject")
    @NonNull
    public String subject;

    @ColumnInfo(name = "text")
    @NonNull
    public String text;

    @ColumnInfo(name = "photo")
    public int photo;

    @ColumnInfo(name = "isDone")
    public boolean isDone;

    public Task(String date, String subject, String text) {
        this.date = date;
        this.subject = subject;
        this.text = text;
    }

//    @SuppressLint("SimpleDateFormat")
//    public Task(String subject, String text) {
//        this(new SimpleDateFormat("yyyy-mm-dd").format(Calendar.getInstance().getTimeInMillis()), subject, text);
//    }
}
