package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidnetworkinglab.lab3b4.ApiService;
import com.example.androidnetworkinglab.lab3b4.Contact;
import com.example.androidnetworkinglab.lab3b4.ContactList;
import com.example.androidnetworkinglab.lab3b4.InternetConnection;
import com.example.androidnetworkinglab.lab3b4.MyContactAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Lab3b4 extends AppCompatActivity {
    private ListView listView;
    private View parentView;
    private ArrayList<Contact> contactList;
    private MyContactAdapter adapter;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3b4);
        contactList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new
                                                AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View view, int
                                                            position, long id) {
                                                        Snackbar.make(parentView,
                                                                contactList.get(position).getName() + " => " +
                                                                        contactList.get(position).getPhone(),
                                                                Snackbar.LENGTH_LONG).show();
                                                    }
                                                });
        Toast toast = Toast.makeText(getApplicationContext(),
                "Bai4 ?1", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        FloatingActionButton fab = (FloatingActionButton)
                findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if
                (InternetConnection.checkConnection(getApplicationContext())) {
                    final ProgressDialog dialog;
                    dialog = new ProgressDialog(Lab3b4.this);

                    dialog.setTitle("?2");

                    dialog.setMessage("?3");
                    dialog.show();
                    //Creating an object of our api interface
//                    ApiService api = RetroClient.getApiService();
                    // Calling JSON

                    //Call<ContactList> call = api.getMyJSON();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.6/NetWorking/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Log.e("zxc",  "test2");
                    ApiService apiService = retrofit.create(ApiService.class);

                    Log.e("zxc",  "test3");
                    Call<ContactList> calls = apiService.getMyJSON();
                    Log.e("zxc",  "test4");
                    calls.enqueue(new Callback<ContactList>() {
                        @Override
                        public void onResponse(Call<ContactList> call,
                                               Response<ContactList> response) {
                            //Dismiss Dialog
                            Log.e("zxc",  "test,");
                            Log.e("zxc",  "test"+response.toString());
                            dialog.dismiss();

                            if (response.isSuccessful()) {
                                // Got Successfully
                                contactList = response.body().getContacts();
                                // Binding that List to Adapter
                                adapter = new
                                        MyContactAdapter(Lab3b4.this, contactList);
                                Log.e("zxc", contactList.size() + "zxcasd");
                                listView.setAdapter(adapter);
                            } else {
                                Snackbar.make(parentView,
                                        "?4", Snackbar.LENGTH_LONG).show();
                            }
                        }

                        public void onFailure(Call<ContactList> call, Throwable t) {
                            dialog.dismiss();
                        }

//                        @Override
//                        public void onFailure(Call<ContactList> call,
//                                              Throwable t) {
//                            dialog.dismiss();
//                        }
                    });
                } else {
                    Snackbar.make(parentView,
                            "?5",
                            Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }


}

