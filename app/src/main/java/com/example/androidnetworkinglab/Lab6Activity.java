package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnetworkinglab.Data6.Constants;
import com.example.androidnetworkinglab.Data6.ConvertTemperatureTask;

public class Lab6Activity extends AppCompatActivity implements
        View.OnClickListener {
    EditText edtFC,edtFC2;
    Button btnF, btnC,btnF2,btnC2;
    String strFC = "100";
    TextView tvResult,tvResult2;
    int convertStyle =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6);
        edtFC =  findViewById(R.id.edtF_C);
        btnF =  findViewById(R.id.btnF);
        btnC =  findViewById(R.id.btnC);
        tvResult =  findViewById(R.id.tvress);
        btnF.setOnClickListener(this);
        btnC.setOnClickListener(this);

    }
    private void invokeAsyncTask(String convertParams, String soapAction,
                                 String methodName) {
        new ConvertTemperatureTask(this, soapAction, methodName,
                convertParams).execute(edtFC.getText()
                .toString().trim());
    }
    //call back data from background thread and set to views
    public void callBackDataFromAsyncTask(String result) {
        double resultTemperature = Double.parseDouble(result); //parseString to double
        if (convertStyle == 0) {// C degree to F degree
            tvResult.setText(edtFC.getText().toString().trim() + " degree Celsius = "
                            + String.format("%.2f", resultTemperature) + " degree Fahrenheit");
        } else {// F degree to C degree
            tvResult.setText(edtFC.getText().toString().trim() + " degree Fahrenheit = "
                            + String.format("%.2f", resultTemperature) + " degree Celsius");
        }
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnF:
                invokeAsyncTask("Fahrenheit", Constants.F_TO_C_SOAP_ACTION,
                        Constants.F_TO_C_METHOD_NAME);
                convertStyle = 1;
                break;
            case R.id.btnC:
                invokeAsyncTask("Celsius", Constants.C_TO_F_SOAP_ACTION,
                        Constants.C_TO_F_METHOD_NAME);
                convertStyle = 0;
                break;
        }

    }


}
