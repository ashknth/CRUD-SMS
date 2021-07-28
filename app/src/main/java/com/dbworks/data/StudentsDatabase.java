package com.dbworks.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1,exportSchema = false)
public abstract class StudentsDatabase extends RoomDatabase {

    public abstract StudentsDAO studentsDAO();

}
