package com.todoapp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    SharedPreferences preferences;
    List<User> list;
    UserAdapter adapter;
    boolean sortedByName;
    boolean sortNameDesc;
    boolean sortAgeDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        preferences = getSharedPreferences("settings", MODE_PRIVATE);
        sortedByName=preferences.getBoolean("sortByName",true);
        sortAgeDesc=preferences.getBoolean("sortAgeDesc",false);
        sortNameDesc=preferences.getBoolean("sortNameDesc",true);

        list = new ArrayList<>();
        getUsers();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void getUsers() {
        list.clear();
        if(sortedByName){
            if (sortNameDesc){
                App.getInstance().getDatabase().userDao().getAllOrderNameDesc().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(@Nullable List<User> users) {
                        list.addAll(users);
                        adapter.notifyDataSetChanged();
                    }
                });
            }else{
                App.getInstance().getDatabase().userDao().getAllOrderNameAsc().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(@Nullable List<User> users) {
                        list.addAll(users);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }else if (sortAgeDesc){
            App.getInstance().getDatabase().userDao().getAllOrderAgeDesc().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(@Nullable List<User> users) {
                    list.addAll(users);
                    adapter.notifyDataSetChanged();
                }
            });
        }else{
            App.getInstance().getDatabase().userDao().getAllOrderAgeAsc().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(@Nullable List<User> users) {
                    list.addAll(users);
                    adapter.notifyDataSetChanged();
                }
            });
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_sortNameAsc:
                sortedByName=true;
                sortNameDesc=false;
                getUsers();
                break;
            case R.id.action_sortNameDesc:
                sortedByName=true;
                sortNameDesc=true;
                getUsers();
                break;
            case R.id.action_sortAgeAsc:
                sortedByName=false;
                sortAgeDesc=false;
                getUsers();
                break;
            case R.id.action_sorAgeDesc:
                sortedByName=false;
                sortAgeDesc=true;
                getUsers();
                break;
        }
        preferences.edit().putBoolean("sortByName",sortedByName).apply();
        preferences.edit().putBoolean("sortAgeDesc",sortAgeDesc).apply();
        preferences.edit().putBoolean("sortNameDesc",sortNameDesc).apply();

        Log.d("MyApp", String.valueOf(id));

        return super.onOptionsItemSelected(item);
    }
}
