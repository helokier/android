package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lab2Bai4 extends AppCompatActivity {
    EditText a,b,c;
    TextView TVResui;
    Button btnnhap,btnback;
    String stra,strb,strc;
    public static final String SERVER_NAME =  "http://192.168.1.6/NetWorking/ptb2.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_bai4);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        TVResui = findViewById(R.id.TVResuit2);
        btnnhap = findViewById(R.id.btnnhap);
        btnback= findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (Lab2Bai4.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stra = a.getText().toString();
                strb = b.getText().toString();
                strc = c.getText().toString();
                PTB2AsyncTask task = new PTB2AsyncTask(Lab2Bai4.this,TVResui,strb,strc,stra);
                task.execute();
            }
        });
    }
}