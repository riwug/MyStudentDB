package com.example.aaa.mystudentdb;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Activity just for browsing through students and choosing by clicking one to view / edit.
 *
 *  just to play OOP to get a value from the button from the previous view
 *  and set a layout variable by this value
 *  in the function showStudentListAsTable on BGColor the color of the BG of the list can be set
 *  when we click the upper button on the previous view it will send the str "stdView" to this view
 *  when we click the lower button on the previous view it will send the str "funkyView" to this view
 *  we read the value from "Bundle savedInstanceState" and set the color accordingly to the right color
 *
 * @author ric
 * @author arm
 */
public class BrowseStudents extends AppCompatActivity
{

    //private StudentDatabase studentDatabase;

    // we set this globar variables only for the OOP color playing
    int BGColor;
    int studentCount;
    String [] newString; // wanted to use this string in an cause for search view
    private Helper helper;
    private ListView studentListView;
    // map to store firstname,lastname. Enables us to make a unique query if a student is selected from list
    private HashMap<String, String> studentMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        helper = Helper.getInstance(this.getApplicationContext());

        // String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString = null;
            } else {
                newString = extras.getStringArray("Sendung");
            }
        } else {
            newString = (String []) savedInstanceState.getSerializable("Sendung");
        }

        // total crazy, es ist super wichtig bei if else, dass man die Klammern an die richtigen Stellen macht!
        if (newString[0].equals("stdView") ) {
            BGColor = -1; // -1
        } else if (newString[0].equals("funkyView")) {
            BGColor = -256; // -256
        };

        setContentView(R.layout.activity_browse_students);
        this.setTitle("Students");

        Button closeButton = (Button) findViewById(R.id.btnClose);
        Button newButton = (Button) findViewById(R.id.btnNew);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("new clicked");

                /*

                Todo..

                Intent intent = new Intent(BrowseStudents.this, StudentView.class);
                String Sendung = "stdView";
                intent.putExtra("Sendung", Sendung);
                startActivity(intent);*/
            }
        });

        studentListView = findViewById(R.id.studentListView);
        String[] studArr = readStudentsFromDatabase();

        // make adapter for view
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //        android.R.layout.simple_list_item_1, studArr);

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, studArr);
        studentListView.setAdapter(adapter);
        studentListView.setBackgroundColor(BGColor);

        // textfield to display counts list items
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTextSize(20);
        textView.setText(studArr.length + " entries");

        // toDO...
        // now only item ( name ) is clickable. I want the whole cell / row to be clickable

        OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String str = studentListView.getItemAtPosition(position).toString();

                String[] words = str.split(" ");
                String lastname = words[0];
                String firstname = words[1];

                Intent intent = new Intent(BrowseStudents.this, StudentView.class);
                String Sendung2 = "stdView";
                intent.putExtra("lastname", lastname);
                intent.putExtra("firstname", firstname);
                startActivity(intent);
            }
        };
        studentListView.setOnItemClickListener(mMessageClickedHandler);

    }

    // will create an String Array with all Students in the DB
    public String[] readStudentsFromDatabase()
    {
        ArrayList<Student> allStudents = new ArrayList<Student>();

        // when putExtra is IDSearch call method findByFirstName
        if (newString[0].equals("IDSearch")) {allStudents = helper.getSearchResultForFirstName(newString[1]);}
        if (allStudents.size()>0) {

        } else {
        }
        if (newString[0].equals("stdView")) {allStudents = helper.getStudentList();}
        if (newString[0].equals("funkyView")) {allStudents = helper.getStudentList();}

        Iterator<Student> iter = allStudents.iterator();

        studentCount = allStudents.size();

        // for displaying the list, we need a String for every row (ArrayAdapter needs a String
        // array)
        String [] studentNamesArray = new String[allStudents.size()];   // important: initialize String array
        int count = 0;
        while (iter.hasNext()) {
            System.out.println("element added");
            Student stud = iter.next();
            studentNamesArray[count] = (stud.getLast_name() + " " + stud.getFirst_name());
            studentMap.put(stud.getInstrument(), stud.getFirst_name());
            //studentMap.put(stud.getLast_name(), stud.getFirst_name());
            count++;
        }
        return studentNamesArray;
    }
}