package com.todoapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.todoapp.Task;
import com.todoapp.User;

@Database(entities = {Task.class, User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
