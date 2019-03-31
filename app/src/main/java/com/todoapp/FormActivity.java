package com.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Date;

public class FormActivity extends AppCompatActivity {

    EditText editTitle;
    RadioGroup radioGroup;
    int status;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        editTitle = findViewById(R.id.editTitle);
        radioGroup = findViewById(R.id.radioGroup);

        task = (Task) getIntent().getSerializableExtra("task");
        if (task != null) {
            editTitle.setText(task.getTitle());
            switch (task.getStatus()) {
                case 0:
                    radioGroup.check(R.id.radio1);
                    break;
                case 1:
                    radioGroup.check(R.id.radio2);
                    break;
                case 2:
                    radioGroup.check(R.id.radio3);
                    break;
            }
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio1:
                        status = 0;
                        break;
                    case R.id.radio2:
                        status = 1;
                        break;
                    case R.id.radio3:
                        status = 2;
                        break;
                }
            }
        });
    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        Log.d("MyApp","Title "+title);
        if (task != null) {
            task.setTitle(title);
            task.setStatus(status);
            App.getInstance().getDatabase().taskDao().update(task);
        } else {
            Task task = new Task();
            task.setTitle(title);
            task.setStatus(status);
            task.setTime(System.currentTimeMillis());
            App.getInstance().getDatabase().taskDao().insert(task);
        }
        finish();
    }
}
