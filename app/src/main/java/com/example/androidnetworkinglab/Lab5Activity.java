package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidnetworkinglab.Data5.activity.AddProductActivity;
import com.example.androidnetworkinglab.Data5.activity.AllProductsActivity;


public class Lab5Activity extends AppCompatActivity {
    private Button btnViewProducts;
    private Button btnAddProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5);
        btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
        btnAddProduct = (Button) findViewById(R.id.btnAddProduct);
        btnViewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lab5Activity.this, AllProductsActivity.class);
                startActivity(intent);
            }
        });
        btnAddProduct.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent intent = new Intent(Lab5Activity.this,
                    AddProductActivity.class); startActivity(intent);
        } });


    }
}