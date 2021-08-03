package com.example.androidnetworkinglab.Data5.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidnetworkinglab.Data5.asyncTask.CreateNewProductTask;
import com.example.androidnetworkinglab.Data5.asyncTask.LoadAllProductsTask;
import com.example.androidnetworkinglab.R;


public class AddProductActivity extends AppCompatActivity {

    private EditText edtName, edtPrice, edtDescription; private Button btnAdd;
    String strName,strPrice,strDes;
    CreateNewProductTask crete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_add_product);
        edtName = (EditText)findViewById(R.id.edtProductName);
        edtPrice = (EditText)findViewById(R.id.edtProductPrice);
        edtDescription = (EditText)findViewById(R.id.edtProductDes);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        crete = new CreateNewProductTask(AddProductActivity.this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strName = edtName.getText().toString();
                strPrice = edtPrice.getText().toString();
                strDes = edtDescription.getText().toString();
                crete.execute(strName,strPrice,strDes);

            } });
    }
}