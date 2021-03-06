package com.example.aaa.mystudentdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StudentDAO
{
    @Query("SELECT * FROM Student")
    List<Student> getAll();

    @Query("SELECT * FROM Student WHERE id IN (:studentIds)")
    List<Student> loadAllByIds(int[] studentIds);

    @Query("SELECT * FROM Student WHERE first_name LIKE :first")
    List<Student> findByFirstName(String first);

    @Query("SELECT * FROM Student WHERE first_name LIKE :name OR last_name LIKE :name")
    List<Student> findByFirstOrLastName(String name);

    @Query("SELECT * FROM Student WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    Student findByName(String first, String last);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student
    );

}