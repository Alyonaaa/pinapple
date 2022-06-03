package com.example.pinapple_.model;

import java.util.ArrayList;
import java.util.List;

public class TasksService {
    public interface Listener {
        void onChange();
    }

    private List<Task> tasks = new ArrayList<>();
    private List<Listener> listeners = new ArrayList<>();

    {
//        tasks.add(new Task("math", "some text for first task"));

    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyAllDataChanged();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    private void notifyAllDataChanged() {
        for (Listener l : listeners) {
            l.onChange();
        }
    }
}
