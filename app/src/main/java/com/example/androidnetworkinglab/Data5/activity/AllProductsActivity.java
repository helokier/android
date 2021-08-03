package com.example.androidnetworkinglab.Data5.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;


import com.example.androidnetworkinglab.Data5.asyncTask.LoadAllProductsTask;
import com.example.androidnetworkinglab.R;


public class AllProductsActivity extends AppCompatActivity {

    private ListView lvProducts;
    LoadAllProductsTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        lvProducts = (ListView)findViewById(R.id.listProducts);
        task = new LoadAllProductsTask(AllProductsActivity.this,lvProducts);
        task.execute(); }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data); if(resultCode == 100){
// if result code 100 is received
// means user edited/deleted product // reload this screen again
            Intent intent = getIntent(); finish();
            startActivity(intent);
        }
    }
}