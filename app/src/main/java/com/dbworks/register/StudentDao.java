package com.dbworks.register;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dbworks.register.StudentEntity;

@Dao
public interface StudentDao {
    @Insert
    void insert(StudentEntity studentEntity);

    @Query("SELECT *from studentDB where studentEmail=(:studentEmail) and studentPassword=(:studentPassword)")
    StudentEntity login(String studentEmail, String studentPassword);



}
