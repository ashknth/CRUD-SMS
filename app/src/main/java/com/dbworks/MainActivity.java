package com.dbworks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.dbworks.data.StudentRepository;
import com.dbworks.register.RegisterActivity;
import com.dbworks.register.StudentDao;
import com.dbworks.register.StudentDatabase;
import com.dbworks.register.StudentEntity;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    public EditText uname;
    public EditText password;
    public Button login_btn;
    public TextView no_account;
    public ImageButton btn_fingerprint;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.btn_login);
        no_account = findViewById(R.id.noAccount);
        btn_fingerprint=findViewById(R.id.fingerprint_btn);

        no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        //Biometric login system
        Executor executor= Executors.newSingleThreadExecutor();

        FragmentActivity activity = this;
        final androidx.biometric.BiometricPrompt biometricPrompt = new androidx.biometric.BiometricPrompt(activity, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString)
            {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == androidx.biometric.BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    // user clicked negative button
                } else {
                    //TODO: Called when an unrecoverable error has been encountered and the operation is complete.
                }
            }

            //Biometric
            @Override
            public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                //TODO: Called when a biometric is recognized.
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(MainActivity.this, "An error Occurred", Toast.LENGTH_SHORT).show();
                //TODO: Called when a biometric is valid but not recognized.
            }

        });

        btn_fingerprint.setOnClickListener(new View.OnClickListener()
        {
            final androidx.biometric.BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Student App Login")
                    .setSubtitle("Touch the Sensor")
                    .setDescription("Fingerprint to authenticate")
                    .setNegativeButtonText("Use PIN")
                    .build();

            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });

        //end of biometric login
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = uname.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (un.isEmpty() || pass.isEmpty()) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("un", un);
                    editor.putString("pass", pass);
                    editor.apply();
                   // Log.d(MyPREFERENCES, un);
                    //Log.d(MyPREFERENCES, pass);
                    Toast.makeText(MainActivity.this, "Fill All", Toast.LENGTH_SHORT).show();

                } else {
                    StudentDatabase studentDatabase = StudentDatabase.getStudentDatabase(getApplicationContext());
                    StudentDao studentDao = studentDatabase.studentDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            StudentEntity studentEntity = studentDao.login(un, pass);
                            if (studentEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Wrong Email n passwroed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("name",un);
                                startActivity(intent);
                            }
                        }
                    }).start();

                }

            }

        });
        //SharedPreferences
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //sharedpreferences data fetch
        SharedPreferences getSharedPreferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        String un= getSharedPreferences.getString("un","un");
        String pass= getSharedPreferences.getString("pass","pass");
        uname.setText(un);
        //password.setText(pass);
        Log.d(MyPREFERENCES, un);
        //Log.d(MyPREFERENCES, pass);

        //repository code
        StudentRepository studentRepository= new StudentRepository(getApplicationContext());

    }
}