package com.example.aaa.mystudentdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.arch.persistence.room.Room;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class BrowseStudents extends AppCompatActivity {

    private StudentDatabase studentDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_students);
        this.setTitle("Browse students");

        initDatabase();

        ArrayList<Student> allStudents = (ArrayList) studentDatabase.studentDao().getAll();
        Iterator<Student> iter = allStudents.iterator();

        // for displaying the list, we need a String for every row (ArrayAdapter needs a String array)
        String [] studentNamesArray = new String[allStudents.size()];   // important: initialize String array
        int count = 0;
        while (iter.hasNext()) {
            System.out.println("element added");
            Student stud = iter.next();
            studentNamesArray[count] = (stud.getLast_name() + " " + stud.getFirst_name() + " " + stud.getTimestampOnCreate());
            count++;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, studentNamesArray);

        ListView studentListView = (ListView) findViewById(R.id.studentListView);
        studentListView.setAdapter(adapter);

        Button closeButton = (Button) findViewById(R.id.btnClose);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

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
        student1.setContact(firstname + "@pornsite.com");
        student1.setTimestampOnCreate(timestampOnCreate());
        return student1;
    }
}
