package com.example.aaa.mystudentdb;


import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.ArrayList;

/**
 *  Helper class for instantiating the database with test data and... other stuff
 *
 *  This is a Singleton, so we make the constructor private. Other classes only can get the helper object by calling
 *  the method getInstance().
 *
 * @author arm
 * @author ric
 */

public class Helper
{

    private static Helper instance = null;

    private Context context;
    private StudentDatabase studentDatabase;

    /**
     * In a Singleton, the constructor is private.
     */
    private Helper(Context context) {
        this.context = context;
        initDatabase();
    }

    public static Helper getInstance(Context context)
    {
        if (instance != null)
            return instance;
        else {
            instance = new Helper(context);
            return instance;
        }
    }

    public StudentDAO getStudentDAO()
    {
        return studentDatabase.studentDao();
    }

    // init database
    private void initDatabase() {
        studentDatabase = Room.databaseBuilder(context,
                StudentDatabase.class, "studentDB").allowMainThreadQueries().build();

        if (studentDatabase.studentDao().getAll().size() == 0) // if database is empty, write sample data
            writeSampleStudentsToDatabase();
    }

    private void writeSampleStudentsToDatabase()
    {
        Student exampleStudent = generateSampleStudent("Erster");
        studentDatabase.studentDao().insertAll(exampleStudent);
        exampleStudent = generateSampleStudent("Zweiter");
        studentDatabase.studentDao().insertAll(exampleStudent);
        exampleStudent = generateSampleStudent("Riwu");
        studentDatabase.studentDao().insertAll(exampleStudent);
        exampleStudent = generateSampleStudent("Armin");
        studentDatabase.studentDao().insertAll(exampleStudent);
        exampleStudent = generateSampleStudent("Minion");
        studentDatabase.studentDao().insertAll(exampleStudent);
    }

    // will get "all students"
    public ArrayList<Student> getStudentList()
    {
        return (ArrayList) studentDatabase.studentDao().getAll();
    }

    // will get "student" with ID
    public ArrayList<Student> getSearchResultForFirstName(String first)
        {
        if (((ArrayList) studentDatabase.studentDao().findByFirstName(first)).size() == 0) {
            return null;
        } else {
            return (ArrayList) studentDatabase.studentDao().findByFirstName(first);
        }
        }

    public String timestampOnCreate()
    {
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

    /**
     *
     * @param firstname First name of the new Student instance
     * @return returns a new Student object
     */
    protected Student generateSampleStudent(String firstname)
    {
        Student student1 = new Student();
        student1.setFirst_name(firstname);
        student1.setLast_name("Wild");
        student1.setContact(firstname + "@site.com");
        student1.setTimestampOnCreate(timestampOnCreate());
        return student1;
    }

/*
        for(int l=0; l<=500; l++) {
            System.out.println(variable);
        }
        */



}
