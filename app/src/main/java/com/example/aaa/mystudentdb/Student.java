package com.example.aaa.mystudentdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Student
{

    // all columns
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "instrument")
    private Instrument instrument;

    @ColumnInfo(name = "contact")
    private String contact;

    @ColumnInfo(name = "timestampOnCreate")
    private String timestampOnCreate;



    // all getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContact() { return contact; }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInstrument() { return instrument.name(); }

    public void setInstrument(String instrument) {
        if(instrument.equalsIgnoreCase("Piano")) this.instrument = Instrument.Piano;
        if(instrument.equalsIgnoreCase("Guitar")) this.instrument = Instrument.Guitar;
        if(instrument.equalsIgnoreCase("Drums")) this.instrument = Instrument.Drums;
        if(instrument.equalsIgnoreCase("Violin")) this.instrument = Instrument.Violin;
        if(instrument.equalsIgnoreCase("Flute")) this.instrument = Instrument.Flute;
        if(instrument.equalsIgnoreCase("Other")) this.instrument = Instrument.Other;
    }

    public String getTimestampOnCreate() {return timestampOnCreate;}

    public void setTimestampOnCreate(String timestampOnCreate) {
        this.timestampOnCreate = timestampOnCreate;
    }


}