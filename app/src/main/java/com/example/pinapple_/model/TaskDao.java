package com.example.pinapple_.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM tasks WHERE date = :date")
    LiveData<List<Task>> getTasksByDate(String date);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task... tasks);

    @Query("DELETE FROM tasks")
    void deleteAll();

    @Update()
    void update(Task task);

    @Query("UPDATE tasks SET isDone = :doneValue WHERE _id = :taskId")
    void setDone(String doneValue, String taskId);

//    @Transaction
//    void updateDone(Task task) {
//        String doneValue = task.isDone ? "1" : "0";
//        updateDone(doneValue, String.valueOf(task._id));
//    }
}
