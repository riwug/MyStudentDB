package com.example.aaa.mystudentdb;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;


import java.util.ArrayList;
import java.util.HashMap;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Main / initial activity of the application. Contains also main method.
 *
 *  <br>
 *  <br> friendly greetings from the developers
 *  <br><img alt="image desc" src="ARP_logo_small.jpg">
 *
 *      New coding principle applied with ' { ' , see https://gitlab.com/riwug/wikitest/wikis/Coding-conventions
 *
 * @author ric
 * @author arm
 */
public class MainActivity extends AppCompatActivity
{
    Button btnSearch;
    EditText mEditTextFieldUsedAsSearchfieldMainView;
    private Helper helper;
    private ListView studentListView;
    Button btnSearchBSView;

    // map to store firstname,lastname. Enables us to make a unique query if a student is selected from list
    private HashMap<String, String> studentMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("My Student Database");

        // init helper-Class
        helper = helper.getInstance(this.getApplicationContext());

        // Search Button
        // Button important: instantiate with new before creating Listener!!!

        studentListView = findViewById(R.id.studentListView);
        //String[] studArr = readStudentsFromDatabase();
        // make adapter for view
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //        android.R.layout.simple_list_item_1, studArr);

        ArrayListAdapter adapter = new ArrayListAdapter(this, readStudentsAllFromDatabase());
        studentListView.setAdapter(adapter);
        //studentListView.setBackgroundColor(BGColor);


        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Student student = (Student) studentListView.getItemAtPosition(position);

                String lastname = student.getLast_name();
                String firstname = student.getFirst_name();

                Intent intent = new Intent(MainActivity.this, StudentView.class);
                String Sendung = "stdView";
                intent.putExtra("lastname", lastname);
                intent.putExtra("firstname", firstname);
                startActivity(intent);
            }
        };
        studentListView.setOnItemClickListener(mMessageClickedHandler);

        // init text of editTextField
        mEditTextFieldUsedAsSearchfieldMainView   = (EditText)findViewById(R.id.fieldEditText);
        mEditTextFieldUsedAsSearchfieldMainView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // just to get rid of the text when clicked on
                mEditTextFieldUsedAsSearchfieldMainView.setText("");

                // change text of searchbutton
                btnSearch.setText("clear");
             }
        });


        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setText("Search");
        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                btnSearch.setText("clear");
                btnSearch.setBackgroundResource(R.drawable.button_type2);
                String searchedName = (mEditTextFieldUsedAsSearchfieldMainView.getText().toString());
                searchedName = ("%" + searchedName + "%");

                if (mEditTextFieldUsedAsSearchfieldMainView.getText().toString().length() > 0){
                // for checking in search returned students
                    ArrayList<Student> searchResults = searchStudentsFromDatabaseByFirstnameOrLastname(searchedName);
                    if (searchResults == null){
                        Toast t = Toast.makeText(MainActivity.this, R.string.toast_noresults, Toast.LENGTH_SHORT);
                        t.show();

                    }

                    else {
                        ArrayListAdapter adapter = new ArrayListAdapter(MainActivity.this, searchResults);
                        studentListView.setAdapter(adapter);
                        }

                } else {
                    Toast t = Toast.makeText(MainActivity.this, R.string.toast_pleaseenter, Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });
    }


    public ArrayList<Student> readStudentsAllFromDatabase()
    {
        ArrayList<Student> studentsArrayList = new ArrayList<Student>();

        studentsArrayList = helper.getStudentList();

        return studentsArrayList;
    }


    public ArrayList<Student> searchStudentsFromDatabaseByFirstnameOrLastname(String searchName)
    {
        ArrayList<Student> studentsArrayList = new ArrayList<Student>();

        // when putExtra is nameSearch call method findByFirstName
        studentsArrayList = helper.getSearchResultForFirstOrLastName(searchName);


        return studentsArrayList;
    }






}