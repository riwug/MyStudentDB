package com.example.aaa.mystudentdb;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Activity just for browsing through students and choosing by clicking one to view / edit.
 *
 * @author ric
 * @author arm
 */
public class BrowseStudents extends AppCompatActivity {

    //private StudentDatabase studentDatabase;

    // we set this globar variables only for the OOP color playing
    int BGColor;
    int studentCount;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("+++ test +++ test +++ test +++");
        helper = Helper.getInstance(this.getApplicationContext());

        // just to play OOP to get a value from the button from the previous view
        // and set a layout variable by this value
        // in the function showStudentListAsTable on BGColor the color of the BG of the list
        // can be set
        // when we click the upper button on the previous view it will send the str "stdView" to
        // this view
        // when we click the lower button on the previous view it will send the str "funkyView" to
        // this view
        // we read the value from "Bundle savedInstanceState"
        // and set the color accordingly to the right color

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString = null;
            } else {
                newString = extras.getString("Sendung");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("Sendung");
        }

        // total crazy, es ist super wichtig bei if else, dass man die Klammern an die richtigen Stellen macht!
        if (newString.equals("stdView") ) {
            BGColor = -1;
        } else if (newString.equals("funkyView")) {
            BGColor = -256;
        };

        setContentView(R.layout.activity_browse_students);
        this.setTitle("Browse students");

        showStudentListAsTable(); // uses external method readStudentsFromDatabase

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTextSize(20);
        textView.setText("Nr of Entries: " + studentCount);

        Button closeButton = (Button) findViewById(R.id.btnClose);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    

    // will create a tableList and pollute it with the Students from the String Array produced
    // by readStudentsFromDatabase
    private void showStudentListAsTable() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, readStudentsFromDatabase());

        ListView studentListView = (ListView) findViewById(R.id.studentListView);

        // https://developer.android.com/reference/android/graphics/Color
        studentListView.setBackgroundColor(BGColor);

        studentListView.setAdapter(adapter);
    }

    // will create an String Array with all Students in the DB
    private String[] readStudentsFromDatabase() {
        ArrayList<Student> allStudents = helper.getStudentList();
        Iterator<Student> iter = allStudents.iterator();

        studentCount = allStudents.size();

        // for displaying the list, we need a String for every row (ArrayAdapter needs a String
        // array)
        String [] studentNamesArray = new String[allStudents.size()];   // important: initialize String array
        int count = 0;
        while (iter.hasNext()) {
            System.out.println("element added");
            Student stud = iter.next();
            studentNamesArray[count] = (stud.getLast_name() + " " + stud.getFirst_name() + " " + stud.getTimestampOnCreate());
            count++;
        }
        return studentNamesArray;
    }
}