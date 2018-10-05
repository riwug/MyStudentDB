package com.example.aaa.mystudentdb;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import android.view.View.OnClickListener;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private StudentDatabase studentDatabase;
    Button btnBrowseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("My Student Database");

        //studentDatabase = Room.databaseBuilder(getApplicationContext(),
                //StudentDatabase.class, "studentDB").allowMainThreadQueries().build();

        // find specific student
        //System.out.println(studentDatabase.studentDao().findByName("Hans", "Schnejder").getContact() );





        // Button important: instantiate with new before creating Listener!!!
        btnBrowseStudents = (Button) findViewById(R.id.btnBrowseStudents);
        btnBrowseStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBrowseStudentsClicked();
            }
        });
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

    public void btnBrowseStudentsClicked() {
        Intent intent = new Intent(this, BrowseStudents.class);
        startActivity(intent);
    }
}
