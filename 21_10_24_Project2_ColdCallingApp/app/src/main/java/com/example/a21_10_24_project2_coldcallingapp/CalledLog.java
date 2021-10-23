package com.example.a21_10_24_project2_coldcallingapp;

import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CalledLog extends AppCompatActivity {

    private Button mbackRandomButton;
    private ArrayList<Student> called = new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called_log);

        called = getIntent().getParcelableArrayListExtra("Called_ArrayList");

        //Displaying Students
        ListView mListViewCalled = (ListView) findViewById(R.id.listViewCalled);
        CalledStudentListAdapter adapter = new CalledStudentListAdapter(this, R.layout.adapter_view_layout_called_log, called);
        mListViewCalled.setAdapter(adapter);

        mbackRandomButton = (Button) findViewById(R.id.backRandomButton);
        mbackRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}