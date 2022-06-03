package com.example.pinapple_.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DayDao {

    @Query("SELECT date, SUM(isDone) AS countDone, COUNT(*) AS countTasks FROM tasks GROUP BY date")
    LiveData<List<Day>> getDays();
}
