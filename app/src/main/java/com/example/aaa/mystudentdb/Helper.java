package com.example.aaa.mystudentdb;


import android.arch.persistence.room.Room;

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
    /**
     * In a Singleton, the constructor is private.
     */
    private Helper() {}

    public static Helper getInstance()
    {
        if (instance != null)
            return instance;
        else {
            instance = new Helper();
            return instance;
        }
    }

    public String timestampOnCreate()
    {
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

    protected Student generateSampleStudent(String firstname)
    {
        Student student1 = new Student();
        student1.setFirst_name(firstname);
        student1.setLast_name("Wild");
        student1.setContact(firstname + "@pornsite.com");
        student1.setTimestampOnCreate(timestampOnCreate());
        return student1;
    }

/*
        for(int l=0; l<=500; l++) {
            System.out.println(variable);
        }
        */



}
