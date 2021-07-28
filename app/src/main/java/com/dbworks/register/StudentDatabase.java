package com.dbworks.register;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StudentEntity.class},version = 2,exportSchema = false)

public  abstract class StudentDatabase extends RoomDatabase {
    private static final String dbName="student";
    private static StudentDatabase studentDatabase;
    public static synchronized StudentDatabase getStudentDatabase(Context context){
        if (studentDatabase==null)
        {
            studentDatabase= Room.databaseBuilder(context,StudentDatabase.class,dbName)
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build();

        }
return studentDatabase;
    }

public abstract StudentDao studentDao();
}
