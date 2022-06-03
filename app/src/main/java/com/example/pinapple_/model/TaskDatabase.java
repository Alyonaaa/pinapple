package com.example.pinapple_.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {
    abstract TaskDao taskDao();
    abstract DayDao dayDao();

    private static volatile TaskDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 2;

    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TaskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, "task_database")
//                        .addCallback(startDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback startDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseExecutor.execute(() -> {
                TaskDao taskDao = INSTANCE.taskDao();
                taskDao.deleteAll();

                Task task = new Task(
                        new SimpleDateFormat("YYYY-MM-dd").format(Calendar.getInstance().getTimeInMillis()),
                        "информатика",
                        "закончить проект IT ШКОЛА SAMSUNG"
                );
                taskDao.insert(task);
            });
        }
    };
}
