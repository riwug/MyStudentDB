package com.example.aaa.mystudentdb;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;

public class InitDB extends AppCompatActivity {

    private StudentDatabase studentDatabase;

    void initDatabase() {
        studentDatabase = Room.databaseBuilder(getApplicationContext(),
                StudentDatabase.class, "studentDB").allowMainThreadQueries().build();


        // just testdata
        Student exampleStudent = generateSampleStudent("Gina");
        studentDatabase.studentDao().insertAll(exampleStudent);
        exampleStudent = generateSampleStudent("Egon");
        studentDatabase.studentDao().insertAll(exampleStudent);
        exampleStudent = generateSampleStudent("Armin");
        studentDatabase.studentDao().insertAll(exampleStudent);
    }

    private String timestampOnCreate() {
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }


    protected Student generateSampleStudent(String firstname) {
        Student student1 = new Student();
        student1.setFirst_name(firstname);
        student1.setLast_name("Wild");
        student1.setContact(firstname + "@love.com");
        student1.setTimestampOnCreate(timestampOnCreate());
        return student1;
    }

}
