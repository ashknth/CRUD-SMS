package com.dbworks;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dbworks.data.InsertActivity;
import com.dbworks.data.ViewActivity;
import com.dbworks.register.StudentEntity;

public class HomeActivity extends AppCompatActivity {

    TextView tvUser;
    Button btn_insert;
    Button show_data;
    SharedPreferences.Editor editor;
    ImageButton btn_logout;
    StudentEntity studentEntity;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvUser=findViewById(R.id.tv_user);
        btn_logout=findViewById(R.id.btn_logout);
        String username=getIntent().getStringExtra("name");

        tvUser.setText("Hi, "+username);
        btn_insert=findViewById(R.id.insert_data);
       show_data=findViewById(R.id.show_data);

       btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });

        show_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentView= new Intent(HomeActivity.this, ViewActivity.class);
                startActivity(intentView);
            }
        });

        //logout button code
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                // editor.clear();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        HomeActivity.this);

                // set title
                alertDialogBuilder.setTitle("Logging Out");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                HomeActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                //sharedprefs
                //editor.apply();
                  //startActivity(new Intent(HomeActivity.this,MainActivity.class));
               //finish();
            }
        });



    }
}