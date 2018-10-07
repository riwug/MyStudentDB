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


/**
 * Main / initial activity of the application. Contains also main method.
 *
 *  <br>
 *  <br> friendly greetings from the developers
 *  <br><img alt="image desc" src="ARP_logo_small.jpg">
 *
 * @author ric
 * @author arm
 */
public class MainActivity extends AppCompatActivity {

    private StudentDatabase studentDatabase;
    Button btnBrowseStudents;
    Button btnBrowseStudents2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("My Student Database");

        // Button1 Standard
        // Button important: instantiate with new before creating Listener!!!
        btnBrowseStudents = (Button) findViewById(R.id.btnBrowseStudents);
        btnBrowseStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBrowseStudentsClicked();
            }
        });

        // Button2 Funky
        // Button important: instantiate with new before creating Listener!!!
        btnBrowseStudents2 = (Button) findViewById(R.id.btnBrowseStudents2);
        btnBrowseStudents2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBrowseStudentsClickedFunky();
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

    // putExtra will send additional data to the intent (view)
    // Button1 Standard
    public void btnBrowseStudentsClicked() {
        Intent intent = new Intent(MainActivity.this, BrowseStudents.class);
        String Sendung = "stdView";
        intent.putExtra("Sendung", Sendung);
        startActivity(intent);
    }

    // putExtra will send additional data to the intent (view)
    // Button2 Funky
    public void btnBrowseStudentsClickedFunky() {
        Intent intent = new Intent(MainActivity.this, BrowseStudents.class);
        String Sendung = "funkyView";
        intent.putExtra("Sendung", Sendung);
        startActivity(intent);

    }

}