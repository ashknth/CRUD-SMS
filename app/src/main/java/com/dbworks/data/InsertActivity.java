package com.dbworks.data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dbworks.R;

public class InsertActivity extends AppCompatActivity {


    EditText student_name,student_contact,student_gender,student_rollno;
    Button btn_submit;
    String sname,sgender,srollno,scontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        student_name=findViewById(R.id.edt_student_name);
        student_gender=findViewById(R.id.edt_gender);
        student_rollno=findViewById(R.id.edt_roll_no);
        student_contact=findViewById(R.id.edt_contact_no);
        btn_submit=findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if( student_name.getText().toString().trim().isEmpty()
                        ||student_gender.getText().toString().trim().isEmpty()
                        ||student_rollno.getText().toString().trim().isEmpty()
                        ||student_contact.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fill Detail", Toast.LENGTH_LONG).show();
                }
                else
                {
                    sname=student_name.getText().toString().trim();
                    sgender=student_gender.getText().toString().trim();
                    srollno=student_rollno.getText().toString().trim();
                    scontact=student_contact.getText().toString().trim();
                    StudentRepository studentRepository= new StudentRepository(getApplicationContext());
                    Student student= new Student(Integer.parseInt(srollno),sname,scontact,sgender);
                    studentRepository.InsertTask(student);

                    student_name.setText(" ");
                    student_gender.setText(" ");
                    student_contact.setText(" ");
                    student_rollno.setText(" ");
                    finish();
                }

            }
        });
    }
}