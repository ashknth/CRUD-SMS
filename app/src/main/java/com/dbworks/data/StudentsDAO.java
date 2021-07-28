package com.dbworks.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dbworks.data.Student;

import java.util.List;

@Dao
public interface StudentsDAO {

    @Insert
    Long insertTask (Student student);

    @Update
    void UpdateTask( Student student);

    @Delete
    void deleteTask (Student student);

    //asfer the view is made and CustomAdapter is successfull, query must be fired to fetch data and show it
    @Query("select * from student order by roll_no asc")
    List<Student> getAll();


}
