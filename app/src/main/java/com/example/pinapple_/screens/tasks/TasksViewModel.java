package com.example.pinapple_.screens.tasks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pinapple_.model.Day;
import com.example.pinapple_.model.Task;
import com.example.pinapple_.model.TaskRepository;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private final LiveData<List<Task>> allTasks;

    public TasksViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        allTasks = taskRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<Task>> getTasksByDate(String date) {
        return taskRepository.getTasksByDate(date);
    }

    public LiveData<List<Day>> getDays() {
        return taskRepository.getDays();
    }

    public void insert(Task task) {
        taskRepository.insert(task);
    }

    public void update(Task task) {
        taskRepository.update(task);
    }

    public void setDone(Task task) {
        taskRepository.setDone(task);
    }
}
