package com.example.pinapple_.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> tasks;
    private LiveData<List<Day>> days;

    public TaskRepository(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getDatabase(application);
        taskDao = taskDatabase.taskDao();
        tasks = taskDao.getAllTasks();
        days = taskDatabase.dayDao().getDays();
        populateTextData();
    }

    public LiveData<List<Task>> getAllTasks() {
        return tasks;
    }

    public LiveData<List<Day>> getDays() {
        return days;
    }

    public LiveData<List<Task>> getTasksByDate(String date) {
        return taskDao.getTasksByDate(date);
    }


    public void insert(Task... tasks) {
        TaskDatabase.databaseExecutor.execute(() -> {
            taskDao.insert(tasks);
        });
    }

    public void populateTextData() {
        TaskDatabase.databaseExecutor.execute(() -> {
            taskDao.deleteAll();

            Task task1 = new Task("2022-06-03", "информатика", "закончить проект IT ШКОЛА SAMSUNG");
            Task task2 = new Task("2022-06-03", "информатика", "закончить презентацию к проекту IT ШКОЛА SAMSUNG");
            Task task3 = new Task("2022-06-06", "математика", "подготовится к ОГЭ");

            taskDao.insert(task1, task2, task3);
        });
    }

    public void update(Task task) {
        TaskDatabase.databaseExecutor.execute(() -> {
           taskDao.update(task);
        });
    }

    public void setDone(Task task) {
        TaskDatabase.databaseExecutor.execute(() -> {
            String doneValue = task.isDone ? "1" : "0";
            taskDao.setDone(doneValue, String.valueOf(task._id));
        });
    }
}
