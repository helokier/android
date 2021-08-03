package com.example.androidnetworkinglab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Lab8Activity extends AppCompatActivity {
    private AppCompatButton btn_login,btn_register;
        private EditText edt_email, edt_password;
TextView bar,go;
    private SharedPreferences pref;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab8);
        edt_email = (EditText) findViewById(R.id.et_email);
        edt_password = (EditText) findViewById(R.id.et_password);
        btn_register = (AppCompatButton) findViewById(R.id.btn_register);
        btn_login = (AppCompatButton) findViewById(R.id.btn_login);
        bar = (TextView) findViewById(R.id.progress);
        go = (TextView) findViewById(R.id.next);
        go.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab8Activity.this, TodoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab8Activity.this, ResetActivity.class);
                startActivity(intent);
                finish();
            }
        });
        auth =FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dangky();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               login();
            }
        });

    }
    public void dangky(){
        String user = edt_email.getText().toString();
        String pass = edt_password.getText().toString();
        auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener(){

            @Override
            public void onComplete(@NonNull  Task task) {
                if (task.isComplete()){
                    Toast.makeText(Lab8Activity.this, "SignIn Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Lab8Activity.this, ResetActivity.class);
                    startActivity(intent);

                }

            }
        });
    }
    public void login(){
        String user = edt_email.getText().toString();
        String pass = edt_password.getText().toString();
        auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener(){
            @Override
            public void onComplete(@NonNull  Task task) {
                if (task.isSuccessful()){
                    Toast.makeText(Lab8Activity.this, "Login Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Lab8Activity.this, MainLab8Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Lab8Activity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}