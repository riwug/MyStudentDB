package com.example.aaa.mystudentdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentView extends AppCompatActivity {

    Helper helper;
    TextView textViewLastname;
    TextView textViewFirstname;

    TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = Helper.getInstance(this.getApplicationContext());

        String strFirstname;
        String strLastname;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                strLastname = null;
                strFirstname = null;
            } else {
                strLastname = extras.getString("lastname");
                strFirstname = extras.getString("firstname");
            }
        } else {
            strLastname = (String) savedInstanceState.getSerializable("lastname");
            strFirstname = (String) savedInstanceState.getSerializable("firstname");
        }

        setContentView(R.layout.activity_student_view);
        this.setTitle("View or edit student");

        textViewLastname = (TextView) findViewById(R.id.textViewLastname);
        textViewFirstname = (TextView) findViewById(R.id.textViewFirstname);
        textViewLastname.setText(strLastname);
        textViewFirstname.setText(strFirstname);

        textViewData = (TextView) findViewById(R.id.textViewData);

        Student actualStudent = helper.getStudentDAO().findByName(strFirstname, strLastname);
        
        textViewData.setText("contact: " + actualStudent.getContact() + "\n" + "id: " +
                actualStudent.getId() + "\n" + "timestamp: " + actualStudent.getTimestampOnCreate());

        Button closeButton = (Button) findViewById(R.id.btnClose);
        Button saveButton = (Button) findViewById(R.id.btnSave);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
