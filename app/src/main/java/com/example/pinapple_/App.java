package com.example.pinapple_;

import android.app.Application;

import com.example.pinapple_.model.DaysService;
import com.example.pinapple_.model.TasksService;

public class App extends Application {

    public DaysService daysService = new DaysService();
    public TasksService tasksService = new TasksService();
}
