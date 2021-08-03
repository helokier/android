package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Lab3b1 extends AppCompatActivity {
    ListView listView;
    Button btn1;
    Lab3Asyn  lab3Asyn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3b1);
        listView = (ListView) findViewById(R.id.lv1);
        btn1 =  findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (Lab3b1.this,Lab3b2.class);
                startActivity(intent);
            }
        });
        lab3Asyn = new Lab3Asyn(this,listView);
        lab3Asyn.execute();
    }
}