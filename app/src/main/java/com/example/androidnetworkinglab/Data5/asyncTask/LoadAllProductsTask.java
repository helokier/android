package com.example.androidnetworkinglab.Data5.asyncTask;

import android.app.Activity; import android.app.ProgressDialog; import android.content.Context; import android.content.Intent; import android.os.AsyncTask; import android.util.Log;
import android.view.View;
import android.widget.AdapterView; import android.widget.ListView;

import com.example.androidnetworkinglab.Data5.activity.AddProductActivity;
import com.example.androidnetworkinglab.Data5.activity.EditProductActivity;
import com.example.androidnetworkinglab.Data5.adapter.AdapterProduct;
import com.example.androidnetworkinglab.Data5.json.JSONParser;
import com.example.androidnetworkinglab.Data5.models.Product;

import com.example.androidnetworkinglab.Data5.constants.Constants;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList; import java.util.HashMap; import java.util.List;
/**
 * Created by Han on 29/12/2016.
 */
public class LoadAllProductsTask extends AsyncTask<String, String, String> {
    Context context;
    ListView lvProducts;
    ProgressDialog pDialog;
    JSONParser jParser; ArrayList<Product> listProducts;
    JSONArray products = null;
    AdapterProduct adapterProduct;
    public LoadAllProductsTask(Context context, ListView lvProducts) { this.context = context;
        this.lvProducts = lvProducts;
        jParser = new JSONParser();
        listProducts = new ArrayList<>(); }
    @Override
    protected void onPreExecute() { super.onPreExecute();
        pDialog = new ProgressDialog(context); pDialog.setMessage("Please wait..."); pDialog.setCancelable(false); pDialog.show();
    }
    @Override
    protected String doInBackground(String... strings) {
// Building Parameters
        List<HashMap<String, String>> params = new ArrayList<>();
        JSONObject jsonObject = jParser.makeHttpRequest(Constants.url_all_products, "GET", params);
        try {
            int success = jsonObject.getInt("success");
            if (success == 1) {
                Log.d("All Products: ", jsonObject.toString());
                products = jsonObject.getJSONArray(Constants.TAG_PRODUCTS);
                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);
                    // Storing each json item in variable
                    String id = c.getString(Constants.TAG_PID); String name = c.getString(Constants.TAG_NAME);
// creating new Product
                    Product product = new Product(); product.setId(id); product.setName(name); listProducts.add(product);
                }
            } else {
                Intent intent = new Intent(context, AddProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent); }
        } catch (Exception e) { e.printStackTrace();
        }
        return null; }
    @Override
    protected void onPostExecute(String s) { super.onPostExecute(s);
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
        adapterProduct = new AdapterProduct(context, listProducts); lvProducts.setAdapter(adapterProduct);
        lvProducts.setOnItemClickListener(new
         AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 String pid = listProducts.get(i).getId();
                 Intent intent = new Intent(context, EditProductActivity.class);
                 intent.putExtra(Constants.TAG_PID, pid);
                 ((Activity) context).startActivityForResult(intent, 100); }
         });
    }
}

