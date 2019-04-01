package com.todoapp.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.todoapp.Task;
import com.todoapp.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user Order by name desc")
    LiveData<List<User>> getAllOrderNameDesc();

    @Query("SELECT * FROM user Order by name asc")
    LiveData<List<User>> getAllOrderNameAsc();

    @Query("SELECT * FROM user Order by age desc")
    LiveData<List<User>> getAllOrderAgeDesc();

    @Query("SELECT * FROM user Order by age asc")
    LiveData<List<User>> getAllOrderAgeAsc();

    @Insert
    void insert(User user);

    @Insert
    void insertList(List<User> user);
}
