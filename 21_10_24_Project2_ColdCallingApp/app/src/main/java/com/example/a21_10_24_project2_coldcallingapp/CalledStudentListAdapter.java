package com.example.a21_10_24_project2_coldcallingapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CalledStudentListAdapter extends ArrayAdapter<Student> {

    private Context mContext;
    int mResource;

    public CalledStudentListAdapter(Context context, int resource, ArrayList<Student> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String studentName = getItem(position).getStudentName();
        int studentCallCount = getItem(position).getTimesCalled();
        long studentLastCallTime = getItem(position).getLastCallTime();
        String studentFileName = getItem(position).getFileName();

        String studentLastCallTimeString = "";
        if (studentLastCallTime > 0){
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.setTimeZone(TimeZone.getTimeZone("EST"));
            sdf.applyPattern("MMM dd, yyyy [hh:mm:ss]");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(studentLastCallTime);
            studentLastCallTimeString = sdf.format(calendar.getTime());
        }

        Student student = new Student(studentName, studentFileName);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView mStudentName = (TextView) convertView.findViewById(R.id.studentNameDisplay);
        TextView mStudentCallCount = (TextView) convertView.findViewById(R.id.studentCallCount);
        TextView mStudentLastCallTime = (TextView) convertView.findViewById(R.id.studentLastCallTime);
        ImageView mStudentFileName = (ImageView) convertView.findViewById(R.id.studentPictureDisplay);

        mStudentName.setText(studentName);
        mStudentCallCount.setText("Times Called: " + studentCallCount);
        mStudentLastCallTime.setText(studentLastCallTimeString);

        int id = getContext().getResources().getIdentifier(studentFileName, "drawable", getContext().getPackageName());
        Drawable drawable = getContext().getResources().getDrawable(id);
        mStudentFileName.setImageDrawable(drawable);

        return convertView;
    }
}
