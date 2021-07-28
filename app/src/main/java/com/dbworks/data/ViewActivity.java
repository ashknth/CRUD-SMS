package com.dbworks.data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.dbworks.R;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    //Recycler View Activity
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    ArrayList<Student> studentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Bundle output = new Bundle();
        //loading from XML to activity
        recyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new LoadDataTask().execute();
    }


    //load Data task
    class LoadDataTask extends AsyncTask<Void,Void,Void>
    {
        StudentRepository studentRepository;
        List<Student> studentList;


        //three default methods for AsyncTask
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            studentRepository= new StudentRepository(getApplicationContext());

        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentList=studentRepository.getStudents();
            studentArrayList= new ArrayList<>();
            //traverse
            for(int i=0;i<studentList.size();i++)
            {
                studentArrayList.add(studentList.get(i));

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            CustomAdapter customAdapter= new CustomAdapter(studentArrayList, ViewActivity.this);
            recyclerView.setAdapter(customAdapter);
            super.onPostExecute(unused);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}