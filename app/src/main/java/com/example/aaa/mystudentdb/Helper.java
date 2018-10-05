package com.example.aaa.mystudentdb;


import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;


public class Helper  extends AppCompatActivity {

    public String timestampOnCreate() {
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

    protected Student generateSampleStudent(String firstname) {
        Student student1 = new Student();
        student1.setFirst_name(firstname);
        student1.setLast_name("Wild");
        student1.setContact(firstname + "@pornsite.com");
        student1.setTimestampOnCreate(timestampOnCreate());
        return student1;
    }

/*
        for(int l=0; l<=500; l++){
            System.out.println(variable);
        }
        */



}
