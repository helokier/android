package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimeAsynTask extends AppCompatActivity implements View.OnClickListener{
   EditText TIME;
   Button btnRun;
   TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_asyn_task);
        TIME = findViewById(R.id.TIME);
        btnRun = findViewById(R.id.btnRun);
        view = findViewById(R.id.view);
        btnRun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sleeping = TIME.getText().toString();
      new AsynTaskB4(this,view,TIME).execute(sleeping);
    }
}