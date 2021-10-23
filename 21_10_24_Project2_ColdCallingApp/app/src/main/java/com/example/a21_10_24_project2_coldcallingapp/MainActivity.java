package com.example.a21_10_24_project2_coldcallingapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private TextView mCurrentDate;
    private TextView mCurrentTime;
    private TextView mStudentNameDisplay;
    private ImageView mStudentPicture;
    private Button mRandom;
    private Button mAccessCalledLog;
    private Button mAccessUncalledLog;

    private ArrayList<Student> database = new ArrayList<Student>();
    private ArrayList<Student> canCall = new ArrayList<Student>();
    private ArrayList<Student> called = new ArrayList<Student>();
    private ArrayList<Student> uncalled = new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        //Initializing Database Array
        storeData();

        //Initializing Time Display
        mCurrentDate = (TextView) findViewById(R.id.currentDate);
        mCurrentTime = (TextView) findViewById(R.id.currentTime);

        //Initializing Image Display
        mStudentPicture = (ImageView) findViewById(R.id.studentPicture);

        //Initializing Student Name Display
        mStudentNameDisplay = (TextView) findViewById(R.id.studentNameDisplay);

        //Initializing Random Button
        mRandom = (Button) findViewById(R.id.random);
        mRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCanCall();

                if (canCall.size() == 0)
                {
                    //Display Error Message
                    mStudentNameDisplay.setText("No Remaining Uncalled Students");

                    //Set Image Back To Blank
                    String pictureFileName = "white_square";
                    int id = getResources().getIdentifier(pictureFileName, "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(id);
                    mStudentPicture.setImageDrawable(drawable);
                }
                else
                {
                    //Create Random Number In ArrayList Range
                    int temp = (int)(Math.random() * canCall.size());

                    //Set Student Name Display To Correct Name
                    mStudentNameDisplay.setText(canCall.get(temp).getStudentName());
                    canCall.get(temp).setLastCallTime();
                    canCall.get(temp).addTimesCalled();

                    //Set Picture Display To Correct Image
                    String pictureFileName = canCall.get(temp).getFileName();
                    int id = getResources().getIdentifier(pictureFileName, "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(id);
                    mStudentPicture.setImageDrawable(drawable);
                }
            }
        });

        //Initializing Called Log Access Button
        mAccessCalledLog = (Button) findViewById(R.id.calledLog);
        mAccessCalledLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Updating "called" ArrayList To Pass Onto CalledLog
                updateCalled();

                Intent calledIntent = new Intent(MainActivity.this, CalledLog.class);
                calledIntent.putParcelableArrayListExtra("Called_ArrayList", called);
                startActivity(calledIntent);
            }
        });

        //Initializing Uncalled Log Access Button
        mAccessUncalledLog = (Button) findViewById(R.id.uncalledLog);
        mAccessUncalledLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Updating "called" ArrayList To Pass Onto CalledLog
                updateUncalled();

                Intent uncalledIntent = new Intent(MainActivity.this, UncalledLog.class);
                uncalledIntent.putParcelableArrayListExtra("Uncalled_ArrayList", uncalled);
                startActivity(uncalledIntent);
            }
        });

    }

    public void storeData() {
        database.add(new Student("aamir_ali",          "Aamir Ali"));
        database.add(new Student("adrian_yan" ,        "Adrian Yan"));
        database.add(new Student("alex_aney",          "Alex Aney"));
        database.add(new Student("bipra_dey",          "Bipra Dey"));
        database.add(new Student("darren_dong",        "Darren Dong"));
        database.add(new Student("dennis_wang",        "Dennis Wang"));
        database.add(new Student("dhruv_amin",         "Dhruv Amin"));
        database.add(new Student("dultsin",            "Dultsin"));
        database.add(new Student("eden_kogan",         "Eden Kogan"));
        database.add(new Student("eli_bui",            "Eli Bui"));
        database.add(new Student("elie_belkin",        "Elie Belkin"));
        database.add(new Student("evelyn_paskhaver",   "Evelyn Paskhaver"));
        database.add(new Student("fardin_iqbal",       "Fardin Iqbal"));
        database.add(new Student("jerry_he",           "Jerry He"));
        database.add(new Student("kenny_cao",          "Kenny Cao"));
        database.add(new Student("marc_rosenberg",     "Marc Rosenberg"));
        database.add(new Student("matthew_chen",       "Matthew Chen"));
        database.add(new Student("michael",            "Michael"));
        database.add(new Student("ming_lin",           "Ming Ling"));
        database.add(new Student("mohammed_ihtisham",  "Mohammed Ihtisham"));
        database.add(new Student("noam",               "Noam"));
        database.add(new Student("ralf_pacia",         "Ralf Pacia"));
        database.add(new Student("samuel_iskhakov",    "Samuel Iskhakov"));
        database.add(new Student("sean",               "Sean"));
        database.add(new Student("sebastian_marinescu","Sebastian Marinescu"));
        database.add(new Student("selina_li",          "Selina Li"));
        database.add(new Student("shuyue_chen",        "Shuyue Chen"));
        database.add(new Student("tanushri_sundaram",  "Tanushri Sundaram"));
        database.add(new Student("vasu",               "Vasu"));
        database.add(new Student("xinrui_ge",          "Xinrui Ge"));
        database.add(new Student("zhian_maysoon",      "Zhian Maysoon"));
    }

    public void updateCanCall(){
        ArrayList<Student> temp = new ArrayList<Student>(0);

        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).canCallAgain() == true) {
                temp.add(database.get(i));
            }
        }

        canCall = temp;
    }

    public void updateCalled(){
        ArrayList<Student> temp = new ArrayList<Student>(0);

        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).calledWithin24Hr() == true) {
                temp.add(database.get(i));
            }
        }

        called = temp;

    }

    public void updateUncalled(){
        ArrayList<Student> temp = new ArrayList<Student>(0);

        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).calledWithin24Hr() == false) {
                temp.add(database.get(i));
            }
        }

        uncalled = temp;
    }

    @Override
    public void onStart() {
        super.onStart();

        //Setting Up SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));

        //Displaying Date
        sdf.applyPattern("MMM dd, yyyy");
        mCurrentDate.setText(sdf.format(new Date()));

        //Displaying Time
        sdf.applyPattern("HH:mm:ss");
        mCurrentTime.setText("[" + sdf.format(new Date()) + "]");

        Log.d(TAG, "onStart called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }
}