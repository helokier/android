package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lab2Bai3 extends AppCompatActivity {
     EditText canh;
     TextView TVResui;
     Button btnnhap,btnNext;
    String strCanh;
    public static final String SERVER_NAME =  "http://192.168.1.6/NetWorking/hvpost.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_bai3);
        canh = findViewById(R.id.canh );
        TVResui = findViewById(R.id.TVResuit1);
        btnnhap = findViewById(R.id.btnnhap);
        btnNext =findViewById(R.id.btnNext);
        btnnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCanh = canh.getText().toString();
               HVAsyncTask bPost = new HVAsyncTask(Lab2Bai3.this,TVResui,strCanh);
             bPost.execute();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (Lab2Bai3.this,Lab2Bai4.class);
                startActivity(intent);
            }
        });
    }
}