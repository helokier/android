package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lab2Bai2 extends AppCompatActivity implements View.OnClickListener{
        EditText dai,rong;
        TextView TVResuit;
        String link;
        Button btnnhap,btnnext;
    public static final String SERVER_NAME =  "http://192.168.1.6/NetWorking/hinhchunhat_post.php";
        //http://localhost/Networking/hinhchunhat_post.php?chieudai=10&chieurong=20
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_bai2);
        dai = findViewById(R.id.chieudai);
        rong = findViewById(R.id.chieurong);
        TVResuit = findViewById(R.id.TVResuit);
        btnnhap = findViewById(R.id.btnnhap);
        btnnext = findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (Lab2Bai2.this,Lab2Bai3.class);
                startActivity(intent);
            }
        });
      btnnhap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String width = dai.getText().toString().trim();
        String height = rong.getText().toString().trim();
        HCNAsyncTask ASD =
                new HCNAsyncTask(Lab2Bai2.this, width, height,TVResuit);
               ASD.execute();
    }

}