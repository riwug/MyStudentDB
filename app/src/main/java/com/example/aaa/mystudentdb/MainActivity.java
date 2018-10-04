package com.example.aaa.mystudentdb;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

// HALLO HALLO HALLO


public class MainActivity extends AppCompatActivity {

    private StudentDatabase studentDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDatabase = Room.databaseBuilder(getApplicationContext(),
                StudentDatabase.class, "studentDB").allowMainThreadQueries().build();

        // find specific student
        //System.out.println(studentDatabase.studentDao().findByName("Hans", "Schnejder").getContact() );

        // some sample data for testing the database
        Student exampleStudent = generateSampleStudent("Gina");
        studentDatabase.studentDao().insertAll(exampleStudent);
        exampleStudent = generateSampleStudent("Egon");
        studentDatabase.studentDao().insertAll(exampleStudent);
        exampleStudent = generateSampleStudent("Armin");
        studentDatabase.studentDao().insertAll(exampleStudent);

        printDB();


    }

    protected Student generateSampleStudent(String firstname) {
        Student student1 = new Student();
        student1.setFirst_name(firstname);
        student1.setLast_name("Wild");
        student1.setContact(firstname + "@pornsite.com");
        return student1;
    }

    private void printDB() {
        ArrayList<Student> allData = (ArrayList) studentDatabase.studentDao().getAll();
        Iterator<Student> iter = allData.iterator();

        while (iter.hasNext()) {
            Student stud = iter.next();
            System.out.println("*** *** *** ***");
            System.out.println("*** " + stud.getId() + " ***");
            System.out.println("*** " + stud.getFirst_name() + " ***");
            System.out.println("*** " + stud.getLast_name() + " ***");
            System.out.println("*** " + stud.getContact() + " ***");
            System.out.println("*** *** *** ***");
        }

    }
}
