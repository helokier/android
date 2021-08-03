package com.example.androidnetworkinglab;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lab3Asyn extends AsyncTask<Void, Void, Void> {
    public static String  url = "http://192.168.1.6/NetWorking/index.php";
    ArrayList<Contact> contactArrayList;
    ListView listView;
    Context context;
    AdapterContact apdapterlist;
    public Lab3Asyn (Context context,ListView listView){
        this.listView =listView;
        this.context =context;
        contactArrayList = new ArrayList<>();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler http = new HttpHandler();
        String jsonstr = http.convertStreamToString(url);
        if (jsonstr != null){
            try {
                JSONObject jsonObject = new JSONObject(jsonstr);
                JSONArray contacts = jsonObject.getJSONArray("contacts");
                for (int i = 0; i < contacts.length();i++){
                     JSONObject c = contacts.getJSONObject(i);
                     String id  = c.getString("id");
                    String name = c.getString("name");
                    String email  = c.getString("email");
                    String address  = c.getString("address");
                    String gender  = c.getString("gender");
                   JSONObject phone = c .getJSONObject("phone");

                   String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");
                    Contact contact = new Contact();
                    contact.setId(id);
                    contact.setName(name);
                    contact.setEmail(email);
                    contact.setMobile(mobile);
                  contactArrayList.add(contact);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        apdapterlist = new AdapterContact(context,contactArrayList);
        listView.setAdapter(apdapterlist);
        super.onPostExecute(aVoid);
    }
}
