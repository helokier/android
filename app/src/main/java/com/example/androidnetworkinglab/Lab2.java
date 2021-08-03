package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lab2 extends AppCompatActivity {
    TextView Resuit;
    EditText name;
    EditText socre;
    String link;
    Button btnnext;
//    http://192.168.1.7/NetWorking/std_Get.php?name=nam&score=30
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);
        Resuit =  findViewById(R.id.rs);
        name = findViewById(R.id.name);
        socre = findViewById(R.id.Score);
        btnnext = findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (Lab2.this,Lab2Bai2.class);
                startActivity(intent);
            }
        });
        link = "http://192.168.1.6/NetWorking/std_Get.php";
    }
    public void Logdata(View view){
        StudienAsyncTask studienAsyncTask = new StudienAsyncTask(this, link,name.getText().toString(),socre.getText().toString(),Resuit);
        studienAsyncTask.execute();


    }
}