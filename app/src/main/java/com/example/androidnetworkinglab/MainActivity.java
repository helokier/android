package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button lab1,lab2 ,lab3,lab4,lab5,lab6,lab7,lab8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        lab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (MainActivity.this,FlashScreen.class);
                startActivity(intent);
            }
        });
        lab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (MainActivity.this,Lab2.class);
                startActivity(intent);
            }
        });
        lab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (MainActivity.this,Lab3b1.class);
                startActivity(intent);
            }
        });
        lab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (MainActivity.this,Lab4Activity.class);
                startActivity(intent);
            }
        });
        lab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (MainActivity.this,Lab5Activity.class);
                startActivity(intent);
            }
        });
        lab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (MainActivity.this,Lab6Activity.class);
                startActivity(intent);
            }
        });
        lab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (MainActivity.this,Lab7Activity.class);
                startActivity(intent);
            }
        });
        lab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (MainActivity.this,Lab8Activity.class);
                startActivity(intent);
            }
        });

    }
    public void anhxa(){
        lab1 = findViewById(R.id.lab1);
        lab2 = findViewById(R.id.lab2);
        lab3 = findViewById(R.id.lab3);
        lab4 = findViewById(R.id.lab4);
        lab5 = findViewById(R.id.lab5);
        lab6 = findViewById(R.id.lab6);
        lab7 = findViewById(R.id.lab7);
        lab8 = findViewById(R.id.lab8);


    }
}