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

public class UncalledLog extends AppCompatActivity {

    private Button mbackRandomButton;
    private ArrayList<Student> uncalled = new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncalled_log);

        uncalled = getIntent().getParcelableArrayListExtra("Uncalled_ArrayList");

        //Displaying Students
        ListView mListViewUncalled = (ListView) findViewById(R.id.listViewUncalled);
        CalledStudentListAdapter adapter = new CalledStudentListAdapter(this, R.layout.adapter_view_layout_called_log, uncalled);
        mListViewUncalled.setAdapter(adapter);

        mbackRandomButton = (Button) findViewById(R.id.backRandomButton);
        mbackRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}