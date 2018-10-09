package com.example.aaa.mystudentdb;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.KeyEvent;
import android.util.Log;


import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;


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
    Button btnBrowseStudents;
    Button btnBrowseStudents2;
    Button btnSearch;
    EditText mEdit;
    TextView textFieldResult;

    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("My Student Database");

        // init helper-Class
        helper = helper.getInstance(this.getApplicationContext());

        // Button1 Standard
        // Button important: instantiate with new before creating Listener!!!
        btnBrowseStudents = (Button) findViewById(R.id.btnBrowseStudents);
        btnBrowseStudents.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                btnBrowseStudentsClicked();
            }
        });

        // Button2 Funky
        // Button important: instantiate with new before creating Listener!!!
        btnBrowseStudents2 = (Button) findViewById(R.id.btnBrowseStudents2);
        btnBrowseStudents2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                btnBrowseStudentsClickedFunky();
            }
        });

        // Search Button
        // Button important: instantiate with new before creating Listener!!!


        // init textFieldResult without content
        textFieldResult   = (TextView)findViewById(R.id.textViewFieldResults);
        // textFieldResult.setText("");


        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String searchedFirstname = (mEdit.getText().toString());
                searchedFirstname = ("%" + searchedFirstname + "%");
                btnBrowseStudentsClickedSearchForID((searchedFirstname));

                // toDo
                // habe jetzt hier 2 stunden verbraucht um unterschiedliche Messages an den User zu bringen
                // abhängig was in den Feldern steht etc... AHHHHHHHHHH
                // hat nicht geklappt
                /*
                ArrayList<Student> allStudents = new ArrayList<Student>();
                allStudents = helper.getSearchResultForFirstName(searchedFirstname);

                if (allStudents.size()==0){
                    textFieldResult.setText("Fuck YOU");
                } else {

                    searchedFirstname = ("%" + searchedFirstname + "%");
                    btnBrowseStudentsClickedSearchForID((searchedFirstname));
                }*/

            }
        });


        // init text of editTextField
        mEdit   = (EditText)findViewById(R.id.fieldEditText);
        mEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mEdit.setText("");
                // toDO
                //EditText Field will "always" delete content on click.
                //When text already added, edit function should start to
                //change the entered word
            }
        });



    }
    // putExtra will send additional data to the intent (view)
    // Button1 Standard
    public void btnBrowseStudentsClicked()
    {
        Intent intent = new Intent(MainActivity.this, BrowseStudents.class);
        String  [] Sendung = {"stdView","empty"};
        intent.putExtra("Sendung", Sendung);
        startActivity(intent);
    }

    // putExtra will send additional data to the intent (view)
    // Button2 Funky
    public void btnBrowseStudentsClickedFunky()
    {
        Intent intent = new Intent(MainActivity.this, BrowseStudents.class);
        String  [] Sendung = {"funkyView","empty"};
        intent.putExtra("Sendung", Sendung);
        startActivity(intent);

    }

    // putExtra will send additional data to the intent (view)
    // Search Button in Std View
    public void btnBrowseStudentsClickedSearchForID(String searchedFirstname)
    {
        Intent intent = new Intent(MainActivity.this, BrowseStudents.class);
        String[] Sendung = {"IDSearch", searchedFirstname};
        intent.putExtra("Sendung", Sendung);
        startActivity(intent);

    }



    /*
    private void printDB()
    {
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
    } */
}