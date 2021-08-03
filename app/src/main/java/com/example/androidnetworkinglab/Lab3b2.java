package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Lab3b2 extends AppCompatActivity {
    //Url Person Object Json
    Button btnnext;
    private String urlObjectJson = "http://192.168.1.6/NetWorking/person_object.php";

    //Url Person Array Json
    private String urlArrayJson = "http://192.168.1.6/NetWorking/array.php";

    private static String TAG = Lab3b2.class.getSimpleName();
    private Button btnObjectJson, btnArrayJson;
    private TextView tvResult;
    private ProgressDialog dialog;
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3b2);
        btnObjectJson = (Button) findViewById(R.id.btnObject);
        btnArrayJson = (Button) findViewById(R.id.btnArray);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnnext = findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (Lab3b2.this,Lab3b3.class);
                startActivity(intent);
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setMessage("Vui lòng đợi...");
        dialog.setCancelable(false);

        btnObjectJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjectRequest();
            }
        });

        btnArrayJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonArrayRequest();
            }
        });
    }
    private void showDialog() {
        if(!dialog.isShowing()){
            dialog.show();
        }
    }

    private void hideDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    private void makeJsonObjectRequest() {
        showDialog();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlObjectJson,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d(TAG, jsonObject.toString());
                try{
                    // Phân tích cú pháp phản hồi đối tượng json
                    // phản hồi sẽ là một đối tượng json

                    String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");
                    JSONObject phone = jsonObject.getJSONObject("phone");
                    String home = phone.getString("home");
                    String mobile = phone.getString("mobile");

                    jsonResponse = "";
                    jsonResponse += "Name: " +name+ "\n\n";
                    jsonResponse += "Email: " +email+ "\n\n";
                    jsonResponse += "Home: " +home+ "\n\n";
                    jsonResponse += "Mobile:" +mobile+ "\n\n";

                    tvResult.setText(jsonResponse);
                }catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }

                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " +error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void makeJsonArrayRequest() {
        showDialog();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlArrayJson, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                Log.d(TAG, jsonArray.toString());

                // Phân tích cú pháp phản hồi mảng json
                // lặp qua từng đối tượng json
                try {
                    jsonResponse = "";
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject person = (JSONObject)jsonArray.get(i);

                        String name  = person.getString("name");
                        String email = person.getString("email");
                        JSONObject phone = person.getJSONObject("phone");
                        String home = phone.getString("home");
                        String mobile = phone.getString("mobile");

                        jsonResponse += "Name: " +name+ "\n\n";
                        jsonResponse += "Email: " +email+ "\n\n";
                        jsonResponse += "Home: " +home+ "\n\n";
                        jsonResponse += "Mobile: " +mobile+ "\n\n";
                    }

                    tvResult.setText(jsonResponse);

                }catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " +e.getMessage(), Toast.LENGTH_LONG).show();
                }

                hideDialog();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG,"Error: "+ error.getMessage());

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}