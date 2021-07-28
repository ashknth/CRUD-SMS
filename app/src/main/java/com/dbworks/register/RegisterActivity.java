package com.dbworks.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dbworks.MainActivity;
import com.dbworks.R;

public class RegisterActivity extends AppCompatActivity {
    EditText email, phone, password, confPassword;
    Button register;
    TextView login;
    private StudentDao studentDao;
    StudentEntity studentEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confPassword = findViewById(R.id.password_confirm);
        register = findViewById(R.id.register);
        login = findViewById(R.id.do_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        studentDao = Room.databaseBuilder(this, StudentDatabase.class, "student").build().studentDao();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create student entity here
                final StudentEntity studentEntity = new StudentEntity();
                String emails = email.getText().toString().trim();
                studentEntity.setStudentEmail(emails);
                studentEntity.setStudentPhone(phone.getText().toString().trim());
                studentEntity.setStudentPassword(password.getText().toString().trim());
                studentEntity.setStudentConfirmPassword(confPassword.getText().toString().trim());

              /* String studentEmail = email.getText().toString().trim();
                String studentPhone = phone.getText().toString().trim();
                String studentPassword = password.getText().toString().trim();
                String studentPasswordConfirm = confPassword.getText().toString().trim();*/

                if (validateInput(studentEntity) && validatePass(studentEntity)) {
                    /*StudentEntity student= new StudentEntity(studentEmail,studentPassword,studentPhone);
                    studentDao.insert(student);*/
                    StudentDatabase studentDatabase = StudentDatabase.getStudentDatabase(getApplicationContext());
                    StudentDao studentDao = studentDatabase.studentDao();
                    //Registering user
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            studentDao.insert(studentEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Regestration Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(RegisterActivity.this, "Passwords does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateInput(StudentEntity studentEntity) {
        if (studentEntity.getStudentEmail().isEmpty() ||
                studentEntity.getStudentPhone().isEmpty() ||
                studentEntity.getStudentPassword().isEmpty() ||
                studentEntity.getStudentConfirmPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    private Boolean validatePass(StudentEntity studentEntity) {
        if (studentEntity.getStudentPassword().equals(studentEntity.getStudentConfirmPassword())) {
            return true;
        } else {
            return false;
        }

    }
}

