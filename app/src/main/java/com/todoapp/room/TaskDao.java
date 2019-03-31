package com.todoapp.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.todoapp.Task;

import java.util.List;

@Dao
public interface TaskDao {


    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAll();

    @Query("SELECT * FROM task Order by time desc")
    LiveData<List<Task>> getAllOrderDesc();

    @Query("SELECT * FROM task Order by time asc")
    LiveData<List<Task>> getAllOrderAsc();

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

}
