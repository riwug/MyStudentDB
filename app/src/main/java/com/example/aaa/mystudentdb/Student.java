package com.example.aaa.mystudentdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "contact")
    private String contact;

    @ColumnInfo(name = "timestampOnCreate")
    private String timestampOnCreate;


    /* add these later
    @ColumnInfo(name = "personalinfo")
    private String personalinfo;

    @ColumnInfo(name = "targets")
    private String targets;

    @ColumnInfo(name = "abilities")
    private String abilities;

    @ColumnInfo(name = "repertoire")
    private String repertoire;
    */

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTimestampOnCreate() {
        return timestampOnCreate;
    }

    public void setTimestampOnCreate(String timestampOnCreate) {
        this.timestampOnCreate = timestampOnCreate;
    }

    /*
    public String getTimestampOnCreate() {
        return timestampOnCreate;
    }

    // Create a timestamp to write into to db that is more conceivable that all entries
    // are different whereas they all look the same
    public void setTimestampOnCreate(String timestampOnCreate) {
        this.timestampOnCreate = timestampOnCreate;
        }
*/

    /* add these later
    public String getPersonalinfo() {
        return personalinfo;
    }

    public void setPersonalinfo(String personalinfo) {
        this.personalinfo = personalinfo;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getRepertoire() {
        return repertoire;
    }

    public void setRepertoire(String repertoire) {
        this.repertoire = repertoire;
    }
    */
}