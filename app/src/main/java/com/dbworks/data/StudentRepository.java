package com.dbworks.data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import androidx.room.Room;

import com.dbworks.data.Student;
import com.dbworks.data.StudentsDatabase;

import java.util.List;

public class StudentRepository {

    private String DB_NAME = "studentdb";

    private StudentsDatabase studentsDatabase;
    Context context;

    public StudentRepository(Context context) {
        this.context = context;
        studentsDatabase = Room.databaseBuilder(context, StudentsDatabase.class, DB_NAME).build();
        //Toast.makeText(context,"Database created ...", Toast.LENGTH_SHORT).show();
    }


    //Inserting data to the database using asyncTask
    public void InsertTask(final Student student) {
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... voids) {
                studentsDatabase.studentsDAO().insertTask(student);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(context, student.student_name + "  is inserted", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    //getting data from database
    public List<Student> getStudents() {
        List<Student> studentList = studentsDatabase.studentsDAO().getAll();
        return studentList;
    }
    //Update data oon the datbase

    public void UpdateTask(final Student student) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                studentsDatabase.studentsDAO().UpdateTask(student);
                return null;
            }

        }.execute();
    }

    //delete starts from here
    public void DeleteTask(final Student student) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                studentsDatabase.studentsDAO().deleteTask(student);
                return null;
            }

        }.execute();
    }

}

