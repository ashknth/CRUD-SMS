package com.dbworks.data;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dbworks.R;
import com.dbworks.data.Student;
import com.dbworks.data.StudentRepository;

public class StudentActivity extends AppCompatActivity {
    EditText edt_student_name,edt_student_contact,edt_student_gender,edt_student_rollno;
    Button btn_update,btn_delete;
    int srollno;

    String sstudent_name="",scontact_no="",sgender="";
   String new_up_student_name="",new_up_student_contact="",new_up_student_gender="",new_up_student_rollno="";
   String delete_student_name="",delete_student_contact="",delete_student_gender="",delete_student_rollno="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        edt_student_name=findViewById(R.id.edt_student_name);
        edt_student_gender=findViewById(R.id.edt_gender);
        edt_student_rollno=findViewById(R.id.edt_roll_no);
        edt_student_contact=findViewById(R.id.edt_contact_no);
        btn_update=findViewById(R.id.btn_update);
        btn_delete=findViewById(R.id.btn_delete);


        //get data from custom adapter using Bundle
        Bundle data_bundle=getIntent().getExtras();
        if (data_bundle!=null)
        {
         sstudent_name=data_bundle.getString("student_name");
         srollno=data_bundle.getInt("roll_no");
         sgender=data_bundle.getString("gender");
         scontact_no=data_bundle.getString("contact_no");
        }

        //now set the above data to the UI of update form
        edt_student_name.setText(sstudent_name+"");
        edt_student_rollno.setText(srollno+"");
        edt_student_gender.setText(sgender+"");
        edt_student_contact.setText(scontact_no+"");

        //Listener to the Update button
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_student_name.getText().toString().isEmpty()||
                edt_student_contact.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fill All Details", Toast.LENGTH_LONG).show();
                }
                else
                    {
                    new_up_student_name = edt_student_name.getText().toString().trim();
                    new_up_student_rollno = edt_student_rollno.getText().toString().trim();
                    new_up_student_contact = edt_student_contact.getText().toString().trim();
                    new_up_student_gender = edt_student_gender.getText().toString().trim();

                    //student repository
                    StudentRepository studentRepository= new StudentRepository(getApplicationContext());
                    Student student= new Student(Integer.parseInt(new_up_student_rollno),new_up_student_name,new_up_student_contact,new_up_student_gender);
                    studentRepository.UpdateTask(student);
                    Toast.makeText(getApplicationContext(),"Values updated...", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        //delete action listener here

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_student_name=edt_student_name.getText().toString().trim();
                delete_student_rollno=edt_student_rollno.getText().toString().trim();
                delete_student_contact=edt_student_contact.getText().toString().trim();
                delete_student_gender=edt_student_gender.getText().toString().trim();

                Student student_to_delete=new Student(Integer.parseInt(delete_student_rollno),delete_student_name,delete_student_contact,delete_student_gender);
                generate_delete_dialog(student_to_delete);
            }
        });

    }
    // alert dialog for deleting the data
    public void generate_delete_dialog(Student student)
    {
        final Student student_about_to_delete=student;
        AlertDialog.Builder builder= new AlertDialog.Builder(StudentActivity.this);
        builder.setTitle("Move to trash");
        builder.setMessage("Do you want to delete?\n"+"Name"+student_about_to_delete.student_name);
        builder.setIcon(R.drawable.delete_img);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete code here
                StudentRepository studentRepository=new StudentRepository(getApplicationContext());
                studentRepository.DeleteTask(student_about_to_delete);
                Toast.makeText(StudentActivity.this,"Values Deleted",Toast.LENGTH_LONG).show();
                finish();;
            }
        });

        //if user wants to cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog deleteDialog=builder.create();
        deleteDialog.show();
    }
}