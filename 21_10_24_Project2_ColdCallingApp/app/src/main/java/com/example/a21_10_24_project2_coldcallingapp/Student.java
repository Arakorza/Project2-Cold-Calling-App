package com.example.a21_10_24_project2_coldcallingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    private String fileName;
    private String studentName;
    private long lastCallTime;
    private int timesCalled;

    public Student (String fileName, String studentName) {
        this.fileName = fileName;
        this.studentName = studentName;
        lastCallTime = 0;
        timesCalled = 0;
    }

    protected Student(Parcel in) {
        fileName = in.readString();
        studentName = in.readString();
        lastCallTime = in.readLong();
        timesCalled = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };


    public boolean canCallAgain() {
        long difference = System.currentTimeMillis() - lastCallTime;
        if (difference - 2400000 > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean calledWithin24Hr() {
        long difference = System.currentTimeMillis() - lastCallTime;
        if (difference - 86400000 < 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void addTimesCalled()
    {
        timesCalled += 1;
    }

    public int getTimesCalled()
    {
        return timesCalled;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFileName() {
        return fileName;
    }

    public long getLastCallTime() {
        return lastCallTime;
    }

    public void setLastCallTime() {
        lastCallTime = System.currentTimeMillis();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fileName);
        parcel.writeString(studentName);
        parcel.writeLong(lastCallTime);
        parcel.writeInt(timesCalled);
    }
}
